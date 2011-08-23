package com.processpuzzle.artifact.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.AccessControlledObject;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.DocumentType;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.HashCodeUtil;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.party.artifact.CompanyList;
import com.processpuzzle.party.artifact.PersonDataSheet;
import com.processpuzzle.party.artifact.PersonList;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.resource.domain.Asset;
import com.processpuzzle.resource.resourcetype.domain.AssetType;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.workflow.activity.domain.Action;

@SuppressWarnings("unchecked")
@XmlSeeAlso( { ArtifactList.class, PersonList.class, CompanyList.class, PersonDataSheet.class } )
@XmlType( name = "Artifact", propOrder = { "name", "type" } )

public abstract class Artifact<A extends Artifact<?>> extends GenericEntity<A> implements Asset, AccessControlledObject, Comparable<Artifact> {
   protected @XmlID @XmlAttribute( required = true ) String name;
   protected @XmlIDREF @XmlAttribute ArtifactType type;
   protected @XmlTransient ArtifactFolder containingFolder;
   protected @XmlTransient String path; 
   protected @XmlTransient Map<Integer, ArtifactVersion> versions = new TreeMap<Integer, ArtifactVersion>();
   protected @XmlTransient List<String> htmlAttributeFormats = new ArrayList<String>();
   protected @XmlTransient Map<String, ArtifactView<?>> availableViews = new HashMap<String, ArtifactView<?>>();
   protected @XmlTransient ProcessPuzzleContext applicationContext;
   protected @XmlTransient User currentUser;
   protected @XmlTransient ProcessPuzzleLocale preferredLocale;
   protected @XmlTransient CommentListFactory commentListFactory;

   // Constructors
   protected Artifact() {
      super();
      init();
   }
   
   protected Artifact( String artifactName, ArtifactType type, User creator ) {
      this();
      
      this.name = artifactName;
      this.type = type;
      instantiateVersion( artifactName, type, creator );
      type.instantiateViewsFor( this );
      initializeViews();
      this.path = artifactName;
   }

   public void read( DefaultUnitOfWork work ) { }
   
   public void update( DefaultUnitOfWork work ) { }
   
   public void delete( DefaultUnitOfWork work ) { }

   // Public accessors
   public int compareTo( Artifact anotherArtifact ) {
      return name.compareTo( anotherArtifact.name );
   }
   
   public ArtifactView<?> findAvailablesView( String name ) {
      return availableViews.get( name );
   }

   public Artifact<?> findRelatedArtifact( String name ) {
      return latest().findRelatedArtifact( name );
   }

   public ArtifactView<?> findViewByName( String searchedViewName ) {
      return availableViews.get( searchedViewName );
   }

   public <V extends ArtifactView> V findViewByClass( Class<V> viewClass ) {
      for( Iterator<String> iter = availableViews.keySet().iterator(); iter.hasNext(); ){
         String viewName = iter.next();
         
         if( viewClass.isInstance( availableViews.get( viewName )))
            return (V) availableViews.get( viewName );
      }
      return null;
   }

   public @Override boolean equals( Object objectToCheck ) {
      if( !(objectToCheck instanceof Artifact )) return false;
      Artifact anotherArtifact = (Artifact) objectToCheck;
      if( !name.equals( anotherArtifact.name )) return false;
//      if( containingFolder != null && !containingFolder.equals( anotherArtifact.containingFolder )) return false;
//      if( !versions.equals( anotherArtifact.versions )) return false;
//      if( !type.equals( anotherArtifact.type )) return false;
//      if( !availableViews.equals( anotherArtifact.availableViews )) return false;
//      if( !htmlAttributeFormats.equals( anotherArtifact.htmlAttributeFormats )) return false;
      return true;
   }

   public abstract String getAsXml();

   public String getAsXml( Class<A> subjectClass, Object subjectObject ) {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      try{
         IBindingFactory bindingFactory = BindingDirectory.getFactory( Action.class );
         IMarshallingContext marshallingContext = bindingFactory.createMarshallingContext();
         marshallingContext.setIndent( 3 );
         marshallingContext.marshalDocument( subjectObject, "UTF-8", null, outputStream );
      }catch( JiBXException e ){
         e.printStackTrace();
      }
      return outputStream.toString();
   }

   public ProcessPuzzleLocale getDefaultLocale() {
      return applicationContext.getDefaultLocale();
   }

   public @Override
   DefaultIdentityExpression<A> getDefaultIdentity() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "nameValue", name );
      DefaultIdentityExpression<A> identity = new ArtifactIdentity<A>( context );
      return identity;
   }

   public ArtifactView<?> getDefaultView() {
      for( Iterator<String> iter = availableViews.keySet().iterator(); iter.hasNext(); ){
         String key = iter.next();
         if( availableViews.get( key ).getType().isDefault() ){
            return availableViews.get( key );
         }
      }
      return null;
   }

   public List<String> getLocales() {
      return applicationContext.getInternalizationContext().getSupportedLocalesList();
   }

   public String getPrefferredLanguage() {
      User loggedInUser = UserRequestManager.getInstance().currentUser();
      if( loggedInUser != null )
         return loggedInUser.getPrefferedLocale().getLanguage();
      else
         return null;
   }

   public PropertyView<A> getPropertyView() {
      return findViewByClass( PropertyView.class );
   }

   public PrintView<A> getPrintView() {
      return findViewByClass( PrintView.class );
   }

   public ArtifactVersion getReservedVersion() {
      if( isReserved() )
         return latest().getNextModification().getTargetVersion();
      else
         return null;
   }

   public ArtifactView<?> getView( String viewName ) {
      return availableViews.get( viewName );
   }

   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, name );
      result = HashCodeUtil.hash( result, containingFolder );
      result = HashCodeUtil.hash( result, versions );

      return result;
   }

   // Public mutators
   public void addComment( Comment comment ) {
      CommentList commentList = getCommentList();
      if( commentList == null ){
         commentList = commentListFactory.create( getName() + "_commentList", new DocumentType( "" ) );
         latest().setCommentList( commentList );
      }
      commentList.appendComment( comment );
   }

   public void addComments( CommentList commentList ) {
      for( Comment comment : commentList.getComments() ){
         this.addComment( comment );
      }
   }

   public void addView( String viewName, ArtifactView<?> view ) {
      availableViews.put( viewName, view );
   }

   public void addComment( Person author, String title, String commentText ) {
      if( (author != null) && (title != null) && (commentText != null) ){
         addComment( new Comment( author, title, commentText ) );
      }
   }

   public void addRelatedArtifact( Artifact<?> relatedArtifact ) {
      latest().addRelatedArtifact( relatedArtifact );
   }

   public void removeRelatedArtifact( Artifact<?> relatedArtifact ) {
      latest().removeRelatedArtifact( relatedArtifact );
   }

   public void instantiateViews() {
      type.instantiateViewsFor( this );
   }
   
   public void removeRelatedArtifact( String artifactName ) {
      latest().removeRelatedArtifact( artifactName );
   }

   public void release( User modifier ) throws VersionControlException {
      if( (latest().getNextModification() != null) && (latest().getNextModification().getModifier().getId().equals( modifier.getId() )) ){
         if( (!latest().isVersionControlled()) && (latest().getNextModification().getTargetVersion() != null) ){
            ArtifactVersion newVersion = versions.get( new Integer( 2 ) );
            latest().getNextModification().release();
            versions.clear();
            versions.put( new Integer( 1 ), newVersion );
         }else
            latest().getNextModification().setEnd( new TimePoint( new Date( System.currentTimeMillis() ) ) );
      }else
         throw new VersionControlException( "" );
   }

   public void renameName( String name ) {
      this.name = name;
   }

   public void reserve( User modifier, String comment ) throws VersionControlException {
      if( (latest().getNextModification() == null) || // verziókezelt
            ((latest().getNextModification() != null) && // nem verzió kezelt
            (latest().getNextModification().getModificationPeriod().getBegin() == null)) ){
         if( (latest().isVersionControlled()) || (!(latest().isVersionControlled()) && (latest().getNextModification() == null)) ){
            // inicializáláskor még ugyanazt kell tenni
            new Modification( latest(), modifier, comment );
         }else if( !(latest().isVersionControlled()) && (latest().getNextModification() != null) ){
            // ez a már legalább 1x lefoglalt nem verzió kezelt ág
            latest().getNextModification().reserve( modifier );
         }
         versions.put( new Integer( versions.size() + 1 ), latest().getNextModification().getTargetVersion() );
      }else
         throw new VersionControlException( "" );
   }

   // Properties
   public boolean isReserved() {
      if( ((latest().getNextModification() != null) && isVersionControlled()) || // verziókezelt
            ((latest().getNextModification() != null && !isVersionControlled()) && // nem
            // verzió
            // kezelt
            (latest().getNextModification().getModificationPeriod().getBegin() != null)) ){
         return true;
      }
      return false;
   }

   public boolean isVersionControlled() {
      return latest().isVersionControlled();
   }

   public void setVersionControlled( boolean isVersionControlled ) {
      latest().setVersionControlled( isVersionControlled );
   }

   public Map<String, ArtifactView<?>> getAvailableViews() {
      return availableViews;
   }

   public ArtifactFolder getContainingFolder() {
      return containingFolder;
      // return latest().getContainingFolder();
   }

   public void setContainingFolder( ArtifactFolder containingFolder ) {
      this.containingFolder = containingFolder;
      // latest().setContainingFolder( containingFolder );
      path = containingFolder.getPath() + ArtifactFolder.PATH_SEPARATOR + name;
   }

   public Date getCreation() {
      return latest().getCreation();
   }

   public void setCreation( Date creation ) {
      latest().setCreation( creation );
   }

   public CommentList getCommentList() {
      return latest().getCommentList();
   }

   public String getPath() { return path; }

   public User getLastModifier() {
      if( latest().getPreviousModification() != null ){
         return latest().getPreviousModification().getModifier();
      }else return latest().getCreator();
   }

   public Date getLastModification() {
      return latest().getModification();
   }

   public String getName() {
      return name;
   }

   public ArtifactVersion getLatestVersion() {
      return latest();
   }

   public Collection<Artifact<?>> getRelatedArtifacts() {
      return latest().getRelatedArtifacts();
   }

   public User getResponsible() {
      return latest().getCreator();
   }

   public void setResponsible( User responsible ) {
      latest().setResponsible( responsible );
   }

   public AssetType getType() {
      return type; // return latest().getArtifactType();
   }

   public ArtifactType getArtifactType() {
      return type;
   }

   public Map<Integer, ArtifactVersion> getVersions() {
      return versions;
   }

   // Protected, private helper methods
   protected void defineIdentityExpressions() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "name", name );
      defaultIdentity = new ArtifactNameIdentity<A>( context );

      context = new DefaultQueryContext();
      context.addTextValueFor( "path", getPath() );
      identities.add( defaultIdentity );
      identities.add( new ArtifactFullNameIdentity<A>( context ) );
   }

   protected String formaInternationalizedText( Object value ) {
      if( value != null && !value.equals( "" ) ) return value.toString();
      else return applicationContext.getText( "ui.generic.undefined", preferredLocale );
   }

   protected void init() {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();   
      currentUser = UserRequestManager.getInstance().currentUser();
      preferredLocale = currentUser.getPrefferedLocale();
      if( preferredLocale == null ) preferredLocale = applicationContext.getDefaultLocale();
      
      commentListFactory = applicationContext.getEntityFactory( CommentListFactory.class );
   }
   
   protected void instantiateVersion( String name, ArtifactType type, User creator ) {
      ArtifactVersion version = new ArtifactVersion( name, creator );
      getVersions().put( new Integer( versions.size() + 1 ), version );
   }

   protected ArtifactVersion latest() {
      ArtifactVersion version = versions.get( ((TreeMap<Integer, ArtifactVersion>) versions).lastKey() );
      if( version.getPreviousModification() != null ){
         if( version.getPreviousModification().getModificationPeriod().getEnd() != null ){
            return version;
         }else{
            return version.getPreviousModification().getSourceVersion();
         }
      }else
         return version;
   }
   
   protected String notAvailableResponse() {
      return applicationContext.getText( "ui.generic.undefined", preferredLocale );
   }
   
   private void initializeViews() {
      for( Iterator<String> iter = availableViews.keySet().iterator(); iter.hasNext(); ){
         String key = iter.next();
         ArtifactView<?> aView = availableViews.get( key );
         aView.initializeView();
      }
   }
}