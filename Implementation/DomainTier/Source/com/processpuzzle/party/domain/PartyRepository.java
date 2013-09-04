/*
Name: 
    - PartyRepository

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.processpuzzle.party.domain;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.PersistentObject;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.commons.persistence.query.BooleanOperator;
import com.processpuzzle.commons.persistence.query.BooleanOperators;
import com.processpuzzle.commons.persistence.query.OrderSpecifier;
import com.processpuzzle.commons.persistence.query.OrderingDirections;
import com.processpuzzle.commons.persistence.query.Query;
import com.processpuzzle.party.artifact.PartyDataSheet;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DateAttributeCondition;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.IntegerAttributeCondition;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class PartyRepository extends GenericRepository<Party> {
   private PersonRepository personRepository;
   private CompanyRepository companyRepository;

   public PartyRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public RepositoryResultSet<Party> findAllParty( UnitOfWork work ) {
      return findAll( work );
   }

   public void addParty( UnitOfWork work, Party<?> party ) {
      add( work, party );
   }

   public void updateParty( UnitOfWork work, Party<?> party ) {
      update( work, party );
   }

   public Party<?> getPartyById( UnitOfWork work, Integer id ) {
      return findById( work, id );
   }

   // Organization
   public void addOrganization( UnitOfWork work, Organization theOrganization ) {
      add( work, Organization.class, theOrganization );
   }

   public Organization findOrganizationById( UnitOfWork work, Integer id ) {
      return (Organization) findById( work, Organization.class, id );
   }

   public RepositoryResultSet<Organization> findAllOrganization( UnitOfWork work ) {
      // return (RepositoryResultSet<Organization>) findAll( work, Organization.class );
      return null;
   }

   public Organization findOrganizationByName( UnitOfWork work, String organizationName ) {
      DefaultQuery query = new DefaultQuery( Organization.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "partyName.name", organizationName, ComparisonOperators.EQUAL_TO ) );
      return (Organization) findByQuery( work, query );
   }

   public void updateOrganization( UnitOfWork work, Organization organization ) {
      update( work, Organization.class, organization );
   }

   // OrganizationUnit
   public void addOrganizationUnit( UnitOfWork work, OrganizationUnit theOrganizationUnit ) {
      add( work, OrganizationUnit.class, theOrganizationUnit );
   }

   public OrganizationUnit findOrganizationUnitById( UnitOfWork work, Integer id ) {
      return (OrganizationUnit) findById( work, OrganizationUnit.class, id );
   }

   public RepositoryResultSet<OrganizationUnit> findAllOrganizationUnit( UnitOfWork work ) {
      // return (RepositoryResultSet<OrganizationUnit>) findAll( work, OrganizationUnit.class );
      return null;
   }

   public OrganizationUnit findOrganizationUnitByName( UnitOfWork work, OrganizationName organizationName ) {
      DefaultQuery query = new DefaultQuery( OrganizationUnit.class );
      query.getQueryCondition()
            .addAttributeCondition( new TextAttributeCondition( "partyName.name", organizationName.getName(), ComparisonOperators.EQUAL_TO ) );
      return (OrganizationUnit) findByQuery( work, query );
   }

   // Division
   public void addDivision( UnitOfWork work, Division theDivision ) {
      add( work, Division.class, theDivision );
   }

   public void deleteDivision( UnitOfWork work, Division division ) {
      delete( work, Division.class, division );
   }

   public void updateDivision( UnitOfWork work, Division division ) {
      update( work, Division.class, division );
   }

   public Division findDivisionById( UnitOfWork work, Integer id ) {
      return (Division) findById( work, Division.class, id );
   }

   public RepositoryResultSet<Division> findAllDivision( UnitOfWork work ) {
      // return (RepositoryResultSet<Division>) findAll( work, Division.class );
      return null;
   }

   public Division findDivisionByName( UnitOfWork work, OrganizationName organizationName ) {
      DefaultQuery query = new DefaultQuery( Division.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "partyName", organizationName.getName(), ComparisonOperators.EQUAL_TO ) );
      return (Division) findByQuery( work, query );
   }

   // Department
   public void addDepartment( UnitOfWork work, Department theDepartment ) {
      add( work, Department.class, theDepartment );
   }

   public Department findDepartmentById( UnitOfWork work, Integer id ) {
      return (Department) findById( work, Department.class, id );
   }

   public RepositoryResultSet<Department> findAllDepartment( UnitOfWork work ) {
      // return (RepositoryResultSet<Department>) findAll( work, Department.class );
      return null;
   }

   public Department findDepartmentByName( UnitOfWork work, OrganizationName organizationName ) {
      DefaultQuery query = new DefaultQuery( Department.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "partyName", organizationName.getName(), ComparisonOperators.EQUAL_TO ) );
      return (Department) findByQuery( work, query );
   }

   // ProjectOffice
   public void addProjectOffice( UnitOfWork work, ProjectOffice theProjectOffice ) {
      add( work, ProjectOffice.class, theProjectOffice );
   }

   public void deleteProjectOffice( UnitOfWork work, ProjectOffice ProjectOffice ) {
      delete( work, ProjectOffice.class, ProjectOffice );
   }

   public void updateProjectOffice( UnitOfWork work, ProjectOffice ProjectOffice ) {
      update( work, ProjectOffice.class, ProjectOffice );
   }

   public ProjectOffice findProjectOfficeById( UnitOfWork work, Integer id ) {
      return (ProjectOffice) findById( work, ProjectOffice.class, id );
   }

   public RepositoryResultSet<ProjectOffice> findAllProjectOffice( UnitOfWork work ) {
      // return (RepositoryResultSet<ProjectOffice>) findAll( work, ProjectOffice.class );
      return null;
   }

   public ProjectOffice findProjectOfficeByName( UnitOfWork work, OrganizationName organizationName ) {
      DefaultQuery query = new DefaultQuery( ProjectOffice.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "partyName", organizationName.getName(), ComparisonOperators.EQUAL_TO ) );
      return (ProjectOffice) findByQuery( work, query );
   }

   // Person
   // Company

   // // Address
   // public Collection findAllAdress() {
   // return findAll(Address.class);
   // }
   //
   // public Address findAddressById(Integer id) {
   // return (Address) get(Address.class, id);
   // }
   //
   // public void addAddress(Address theAddress) {
   // add(Address.class, theAddress);
   // }
   //
   // public void addGeographicAddress(GeographicAddress theAddress) {
   // add(GeographicAddress.class, theAddress);
   // }
   //
   // public void updateAddress(Address theAddress) {
   // update(Address.class, theAddress);
   // }
   //
   // public void deleteAddress(Integer aid) {
   // Address adr = findAddressById(aid);
   // if (adr != null) {
   // delete(Address.class, adr);
   // }
   // }
   //
   // // EmailAddress
   //
   // public Collection findAllEmailAdress() {
   // return findAll(EmailAddress.class);
   // }
   //
   // public EmailAddress findEmailAddressById(Integer id) {
   // return (EmailAddress) get(EmailAddress.class, id);
   // }
   //
   // public void addEmailAddress(EmailAddress theEmailAddress) {
   // add(EmailAddress.class, theEmailAddress);
   // }
   //
   // public void updateEmailAddress(EmailAddress theEmailAddress) {
   // update(EmailAddress.class, theEmailAddress);
   // }
   //
   // public void deleteEmailAddress(Integer aid) {
   // EmailAddress adr = findEmailAddressById(aid);
   // if (adr != null) {
   // delete(EmailAddress.class, adr);
   // }
   // }

   // // SoftWareSystem
   //
   // public void addSoftwareSystem(SoftwareSystem system) {
   // add(SoftwareSystem.class, system);
   // }
   //
   // public Collection findAllSoftwareSystem() {
   // return findAll(SoftwareSystem.class);
   // }
   //
   // public Collection findAllOrderedSoftwareSystems(String propertyName) {
   // HashMap<String, String> map = new HashMap<String, String>();
   // map.put(GenericRepository.ORDER, propertyName);
   // return find("from SoftwareSystem", map);
   // }
   //
   // public Collection findAllSoftwareSystemByCompany(OrganizationName
   // companyName) {
   // return find("from Company c where c.organizationName.name = ${name}", new
   // Object[] { "name", companyName });
   // }
   //
   // public SoftwareSystem findSoftwareSystemById(Integer id) {
   // return (SoftwareSystem) get(SoftwareSystem.class, id);
   // }
   //
   // public void updateSoftwareSystem(SoftwareSystem system) {
   // update(SoftwareSystem.class, system);
   // }
   //
   // public void deleteSoftwareSystem(SoftwareSystem softwareSystem) {
   // delete(SoftwareSystem.class, softwareSystem);
   // }

   // Versioned Document

   /*
    * public Collection findAllVersionedDocument() { return findAll(VersionedDocument.class); } public Collection findAllOrderedVersionedDocument(String
    * propertyName) { HashMap map = new HashMap(); map.put(Repository.ORDER, propertyName); return find("from VersionedDocument", map); } public
    * VersionedDocument findVersionedDocumentById(String id) { return (VersionedDocument) get(VersionedDocument.class, new Integer(id)); } public void
    * addVersionedDocument(VersionedDocument theVersionedDocument) { add(VersionedDocument.class, theVersionedDocument); } public void
    * updateVersionedDocument(VersionedDocument theVersionedDocument) { update(VersionedDocument.class, theVersionedDocument); } public void
    * deleteVersionedDocument(String did) { Artifact doc = findVersionedDocumentById(did); if (doc != null) { delete(VersionedDocument.class, doc); } }
    * //NonVersioned Document public Collection findAllNonVersionedDocument() { return findAll(NonVersionedDocument.class); } public Collection
    * findAllOrderedNonVersionedDocument(String propertyName) { HashMap map = new HashMap(); map.put(Repository.ORDER, propertyName); return
    * find("from NonVersionedDocument", map); } public NonVersionedDocument findNonVersionedDocumentById(String id) { return (NonVersionedDocument)
    * get(NonVersionedDocument.class, new Integer(id)); } public void addNonVersionedDocument(NonVersionedDocument theNonVersionedDocument) {
    * add(NonVersionedDocument.class, theNonVersionedDocument); } public void updateNonVersionedDocument(NonVersionedDocument theNonVersionedDocument) {
    * update(NonVersionedDocument.class, theNonVersionedDocument); } public void deleteNonVersionedDocument(String did) { NonVersionedDocument doc =
    * findNonVersionedDocumentById(did); if (doc != null) { delete(NonVersionedDocument.class, doc); } }
    */

   // // Group
   // public Collection findAllGroup() {
   // return findAll(Group.class);
   // }
   //
   // public void addGroup(Group group) {
   // add(Group.class, group);
   // }
   //
   // public Group findGroupById(String id) {
   // return (Group) get(Group.class, new Integer(id));
   // }
   //
   // public Group findGroupByName(String name) {
   // return (Group) get("from Group g where g.name = ${name}", new Object[] {
   // "name", name });
   // }
   //
   // public void updateGroup(Group theGroup) {
   // update(Group.class, theGroup);
   // }
   //
   // public void deleteGroup(String did) {
   // Group group = findGroupById(did);
   // if (group != null) {
   // delete(Group.class, group);
   // }
   // }
   //
   // public Collection findAllOrderedGroup() {
   // HashMap<String, String> map = new HashMap<String, String>();
   // map.put(GenericRepository.ORDER, "name asc");
   // return find("from Group", map);
   // }
   // // PersonGroup
   //
   // public Collection findAllPersonGroup() {
   // return findAll(PersonGroup.class);
   // }
   //
   // public void addPersonGroup(PersonGroup personGroup) {
   // add(PersonGroup.class, personGroup);
   // }
   //
   // public PersonGroup findPersonGroupById(String id) {
   // return (PersonGroup) get(PersonGroup.class, new Integer(id));
   // }
   //
   // public void updatePersonGroup(PersonGroup thePersonGroup) {
   // update(PersonGroup.class, thePersonGroup);
   // }
   //
   // public void deletePersonGroup(String did) {
   // PersonGroup personGroup = findPersonGroupById(did);
   // if (personGroup != null) {
   // delete(PersonGroup.class, personGroup);
   // }
   // }
   //
   // public Collection findAllPersonGroupByGroup(Integer groupId) {
   // return find("from PersonGroup p where p.grp.id = ${id}", new Object[] {
   // "id", groupId });
   // }
   //
   // public Collection findAllPersonGroupByGroupAndByPerson(Integer groupId,
   // Integer personId) {
   // return find("from PersonGroup p where p.grp.id = ${gid} and p.person.id =
   // ${pid}", new Object[] { "gid", groupId, "pid", personId });
   // }
   //
   // public Collection findAllOrderedPersonByGroupId(Integer groupId, String
   // orderName, String orderDirection) {
   // Collection personGroups = findAllPersonGroupByGroup(groupId);
   // List<Person> people = new ArrayList<Person>();
   // for (Iterator iter = personGroups.iterator(); iter.hasNext();) {
   // PersonGroup personGroup = (PersonGroup) iter.next();
   // Person person = personGroup.getPerson();
   // if (((person.getValid().getEnd() == null) || ((person.getValid().getEnd()
   // != null) && (person.getValid().getEnd().getValue()
   // .getTime() > (new GregorianCalendar().getTimeInMillis()))))
   // && (person.getValid().getBegin().getValue().getTime() < (new
   // GregorianCalendar().getTimeInMillis()))) {
   // people.add(person);
   // }
   // }
   // Collections.sort(people);
   // if (orderDirection.equals("desc")) {
   // Collections.reverse(people);
   // }
   // return people;
   // }
   //
   // // Comment
   // public void addComment(Comment comment) {
   // add(Comment.class, comment);
   // }
   //
   // public Comment findCommentById(String id) {
   // return (Comment) get(Comment.class, new Integer(id));
   // }
   //
   // public void updateComment(Comment comment) {
   // update(Comment.class, comment);
   // }
   //
   // public Collection findAllComment() {
   // return findAll(Comment.class);
   // }
   //
   // public void deleteComment(String pid) {
   // Comment p = findCommentById(pid);
   // if (p != null) {
   // delete(Comment.class, p);
   // }
   // }
   // public Collection findPersonByAssignment(String assignment) {
   // HashMap<String, String> map = new HashMap<String, String>();
   // map.put(GenericRepository.WHERE, "p.assignment = '" + assignment + "' and
   // ((p.valid.end is not null and p.valid.end >= current_date()) or
   // (p.valid.end is null) ) and p.valid.begin <= current_date()");
   // map.put(GenericRepository.ORDER, "partyName asc");
   // return find("from Person p", map);
   // }
   //
   // // public void deleteCompanySoftwareSystem(Integer id) {
   // // CompanySoftwareSystem c = (CompanySoftwareSystem)
   // get(CompanySoftwareSystem.class, id);
   // // if (c != null) {
   // // delete(CompanySoftwareSystem.class, c);
   // // }
   // // }
   // //
   // // public CompanySoftwareSystem findCompanySoftwareSystemById(Integer id)
   // {
   // // return (CompanySoftwareSystem) get(CompanySoftwareSystem.class, id);
   // // }
   // //
   // // public void updateCompanySoftwareSystem(CompanySoftwareSystem c) {
   // // update(CompanySoftwareSystem.class, c);
   // // }
   //
   // // Artifact
   //
   // public void addArtifact(Artifact theArtifact) {
   // add(Artifact.class, theArtifact);
   // }
   //
   // public Artifact findArtifactById(Integer id) {
   // return (Artifact) get(Artifact.class, id);
   // }
   //
   // public Artifact findArtifactByName(String artifactName) {
   // return (Artifact) get("from Artifact a where a.name = '" + artifactName +
   // "'");
   // }
   //
   // public Collection findAllArtifact() {
   // return findAll(Artifact.class);
   // }
   //
   // public void updateArtifact(Artifact Artifact) {
   // update(Artifact.class, Artifact);
   // }
   //
   // public void deleteArtifact(Integer pid) {
   // Artifact artifact = findArtifactById(pid);
   // if (artifact != null)
   // delete(Artifact.class, artifact);
   // }
   //
   // public void deleteArtifactByName(String name) {
   // Artifact artifact = findArtifactByName(name);
   // if (artifact != null)
   // deleteArtifact(artifact.getId());
   // }
   //
   // public void addArtifactVersion(ArtifactVersion theArtifactVersion) {
   // add(ArtifactVersion.class, theArtifactVersion);
   // }
   //
   // public ArtifactVersion findArtifactVersionById(String id) {
   // return (ArtifactVersion) get(ArtifactVersion.class, new Integer(id));
   // }
   //
   // public Collection findAllArtifactVersion() {
   // return findAll(ArtifactVersion.class);
   // }
   //
   // public void updateArtifactVersion(ArtifactVersion artifactVersion) {
   // update(ArtifactVersion.class, artifactVersion);
   // }
   //
   // public void deleteArtifactVersion(String pid) {
   // ArtifactVersion p = findArtifactVersionById(pid);
   // if (p != null) {
   // delete(ArtifactVersion.class, p);
   // }
   // }
   //
   // public ArtifactVersion findLatestArtifactVersionByArtifactId(String
   // artifactId) {
   // return (ArtifactVersion) get("from ArtifactVersion a where
   // a.VERSIONED_ARTIFACT_ID = ${artifactId} and a.checkInDate is null",
   // new Object[] { "artifactId", artifactId });
   // }
   //
   // public List findArtifactVersionHistoryByArtifactId(String artifactId) {
   // List artifactVersionHistory = new ArrayList();
   // // Artifact artifact = findArtifactById(new Integer(artifactId));
   // /*
   // * if( artifact instanceof NonVersionedDocument ) { NonVersionedDocument
   // * doc = (NonVersionedDocument)artifact; if( !doc.isCheckedOut() ) {
   // * artifactVersionHistory.add(doc.getLatestVersion()); } } else if
   // * (artifact instanceof VersionedDocument) { VersionedDocument doc =
   // * (VersionedDocument) artifact; for (Iterator iter =
   // * doc.getVersions().entrySet().iterator(); iter.hasNext();) { Object
   // * element = (DocumentVersion) iter.next(); element.ke if(
   // * element.getCheckInDate() != null ) {
   // * artifactVersionHistory.add(element); }
   // */// }
   // // }
   // // Collections.sort(artifactVersionHistory);
   // /*
   // * Artifact artifact = (Artifact) get("from Artifact where id = ${ssId}",
   // * new Object[] {"ssId", artifactId }); if ( artifact != null ) { Map map
   // =
   // * new HashMap(); map.put(Repository.WHERE, "av.VERSIONED_ARTIFACT_ID =
   // * "+artifact.getId()+" and checkInDate is not null");
   // * map.put(Repository.ORDER, "checkInDate desc");
   // *
   // * artifactVersionHistory = (List) find("from ArtifactVersion av", map );
   // }
   // */
   // return artifactVersionHistory;
   // }
   //
   // public void updateDocumentVersion(DocumentVersion documentVersion) {
   // update(DocumentVersion.class, documentVersion);
   // }
   //
   // public DocumentVersion findDocumentVersionById(String id) {
   // return (DocumentVersion) get(DocumentVersion.class, new Integer(id));
   // }
   //
   // public void deleteDocumentVersion(DocumentVersion documentVersion) {
   // if (documentVersion != null) {
   // delete(DocumentVersion.class, documentVersion);
   // }
   // }
   //
   // public void deleteDocumentVersion(String id) {
   // DocumentVersion documentVersion = findDocumentVersionById(id);
   // if (documentVersion != null) {
   // delete(DocumentVersion.class, documentVersion);
   // }
   // }
   //
   // public void addHTMLText(HTMLText htmlText) {
   // add(HTMLText.class, htmlText);
   // }
   //
   // public void updateHTMLText(HTMLText htmlText) {
   // update(HTMLText.class, htmlText);
   // }
   public RepositoryResultSet<Company> findAllOrderedActiveCompany( UnitOfWork work, String propertyName ) {
      // HashMap<String, String> map = new HashMap<String, String>();
      // map.put(GenericRepository.ORDER, propertyName);
      // map
      // .put(
      // GenericRepository.WHERE,
      // "((p.valid.end is not null) and (p.valid.end >= current_date())) or
      // ((p.valid.end is null) and (p.valid.begin <= current_date()))");
      // return find("from Company p", map);

      Calendar cal = Calendar.getInstance();
      Date currentDate = cal.getTime();
      DefaultQuery q = new DefaultQuery( Company.class );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.end", ComparisonOperators.IS_NOT_NULL ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.end", currentDate, ComparisonOperators.GREATER_OR_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.end", ComparisonOperators.IS_NULL ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.begin", currentDate, ComparisonOperators.LESS_OR_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.OR ) );
      q.getQueryOrder().addOrderSpecifier( new OrderSpecifier( propertyName, OrderingDirections.Ascending ) );

      // return (RepositoryResultSet<Company>) findByQuery( work, q );
      return null;
   }

   public void updateProject( UnitOfWork work, Project project ) {
      update( work, Project.class, project );

   }

   // public Collection isValidEmailAddress(String emailAddress) {
   // Collection<Boolean> results = new HashSet<Boolean>();
   // if (get("from EmailAddress e where e.emailAddress = ${emailAddress}", new
   // Object[] { "emailAddress", emailAddress }) == null)
   // results.add(new Boolean(true));
   // else
   // results.add(new Boolean(false));
   // return results;
   // }

   public RepositoryResultSet<Person> findAllOrderedActivePerson( UnitOfWork work, String propertyName ) {
      // TODO Ennek most akkor hogy kell mukodnie?
      // HashMap map = new HashMap();
      // map.put(Repository.ORDER, propertyName);
      // map.put(Repository.WHERE, "((p.valid.end is not null and
      // p.valid.end.value >= current_date()) or (p.valid.end is null) ) and
      // p.valid.begin.value <= current_date()");
      @SuppressWarnings( "unused" )
      DefaultQuery q = new DefaultQuery( Person.class );
      // return (RepositoryResultSet<Person>) findByQuery( work, q );
      return null;
   }

   public void addEmployee( UnitOfWork work, Employee theEmployee ) {
      add( work, Employee.class, theEmployee );
   }

   public Employee findEmployeeById( UnitOfWork work, Integer id ) {
      return (Employee) findById( work, Employee.class, id );
   }

   public RepositoryResultSet<Employee> findAllEmployee( UnitOfWork work ) {
      // return (RepositoryResultSet<Employee>) findAll( work, Employee.class );
      return null;
   }

   public void updateEmployee( UnitOfWork work, Employee theEmployee ) {
      update( work, Employee.class, theEmployee );
   }

   public void deleteEmployee( UnitOfWork work, Employee theEmployee ) {
      delete( work, Employee.class, theEmployee );
   }

   public RepositoryResultSet<Employee> findAllOrderedEmployee( UnitOfWork work, String propertyName ) {
      // HashMap<String, String> map = new HashMap<String, String>();
      // map.put(GenericRepository.ORDER, propertyName);
      // return find("from Employee", map);
      DefaultQuery q = new DefaultQuery( Employee.class );
      q.getQueryOrder().addOrderSpecifier( new OrderSpecifier( propertyName, OrderingDirections.Ascending ) );
      // return (RepositoryResultSet<Employee>) findByQuery( work, q );
      return null;
   }

   @SuppressWarnings( "unchecked" )
   public RepositoryResultSet<Employee> findAllOrderedActiveEmployee( UnitOfWork work, String propertyName, String employeeId ) {
      // HashMap<String, String> map = new HashMap<String, String>();
      // map.put(GenericRepository.ORDER, propertyName);
      // map
      // .put(
      // GenericRepository.WHERE,
      // "((p.valid.endDate is not null and p.valid.endDate >= current_date())
      // or (p.valid.endDate is null) ) and p.valid.startDate <= current_date()
      // and p.id <> "
      // + employeeId);
      // return find("from Employee p", map);
      Calendar cal = Calendar.getInstance();
      Date currentDate = cal.getTime();
      DefaultQuery q = new DefaultQuery( Employee.class );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NOT_NULL ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", currentDate, ComparisonOperators.GREATER_OR_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NULL ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.OR ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.startDate", currentDate, ComparisonOperators.LESS_OR_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addAttributeCondition(
            new IntegerAttributeCondition( "valid.startDate", new Integer( employeeId ), ComparisonOperators.LESS_OR_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryOrder().addOrderSpecifier( new OrderSpecifier( propertyName, OrderingDirections.Ascending ) );
      return (RepositoryResultSet<Employee>) findByQuery( work, Employee.class, q );
   }

   public RepositoryResultSet<Employee> findAllOrderedOutEmployee( UnitOfWork work, String propertyName ) {
      // HashMap<String, String> map = new HashMap<String, String>();
      // map.put(GenericRepository.ORDER, propertyName);
      // map
      // .put(GenericRepository.WHERE,
      // "p.valid.endDate is not null and p.valid.endDate < current_date()");
      // return find("from Employee p", map);
      Date currentDate = Calendar.getInstance().getTime();
      DefaultQuery q = new DefaultQuery( Employee.class );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NOT_NULL ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", currentDate, ComparisonOperators.LESS_THAN ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryOrder().addOrderSpecifier( new OrderSpecifier( propertyName, OrderingDirections.Ascending ) );
      // return (RepositoryResultSet<Employee>) findByQuery( work, q );
      return null;
   }

   public RepositoryResultSet<Employee> findAllOrderedFutureEmployee( UnitOfWork work, String propertyName ) {
      // HashMap<String, String> map = new HashMap<String, String>();
      // map.put(GenericRepository.ORDER, propertyName);
      // map
      // .put(
      // GenericRepository.WHERE,
      // "((p.valid.endDate is not null and p.valid.endDate > current_date()) or
      // (p.valid.endDate is null) ) and p.valid.startDate > current_date()");
      // return find("from Employee p", map);
      Date currentDate = Calendar.getInstance().getTime();
      DefaultQuery q = new DefaultQuery( Employee.class );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NOT_NULL ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", currentDate, ComparisonOperators.GREATER_THAN ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NULL ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.OR ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.startDate", currentDate, ComparisonOperators.GREATER_THAN ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryOrder().addOrderSpecifier( new OrderSpecifier( propertyName, OrderingDirections.Ascending ) );
      // return (RepositoryResultSet<Employee>) findByQuery( work, q );
      return null;
   }

   public RepositoryResultSet<Employee> findOrderedEmployeeByAssignment( UnitOfWork work, String assignment ) {
      // HashMap<String, String> map = new HashMap<String, String>();
      // map.put(GenericRepository.ORDER, "partyName asc");
      // map.put(GenericRepository.WHERE, "e.assignment = '" + assignment +
      // "'");
      // return find("from Employee e", map);
      DefaultQuery q = new DefaultQuery( Employee.class );
      q.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "assignment", assignment, ComparisonOperators.EQUAL_TO ) );
      q.getQueryOrder().addOrderSpecifier( new OrderSpecifier( "partyName", OrderingDirections.Ascending ) );
      // return (RepositoryResultSet<Employee>) findByQuery( work, q );
      return null;
   }

   public RepositoryResultSet<Employee> findAllOrderedActiveLeader( UnitOfWork work ) {
      // HashMap<String, String> map = new HashMap<String, String>();
      // map.put(GenericRepository.ORDER, "partyName asc");
      // map
      // .put(
      // GenericRepository.WHERE,
      // "( p.assignment = 'leader' or p.assignment = 'projectDirector' ) and
      // ((p.valid.endDate is not null and p.valid.endDate >= current_date()) or
      // (p.valid.endDate is null) ) and p.valid.startDate <= current_date()");
      // return find("from Employee p", map);
      Date currentDate = Calendar.getInstance().getTime();
      DefaultQuery q = new DefaultQuery( Employee.class );
      q.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "assignment", "leader", ComparisonOperators.EQUAL_TO ) );
      q.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "assignment", "projectDirector", ComparisonOperators.EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.OR ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NOT_NULL ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", currentDate, ComparisonOperators.GREATER_OR_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NULL ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.OR ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.startDate", currentDate, ComparisonOperators.LESS_OR_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryOrder().addOrderSpecifier( new OrderSpecifier( "partyName", OrderingDirections.Ascending ) );
      // return (RepositoryResultSet<Employee>) findByQuery( work, q );
      return null;
   }

   public Employee findProjectDirector( UnitOfWork work ) {
      // HashMap<String, String> map = new HashMap<String, String>();
      // map.put(GenericRepository.ORDER, "partyName asc");
      // map
      // .put(
      // GenericRepository.WHERE,
      // "p.assignment = 'projectDirector' and ((p.valid.endDate is not null and
      // p.valid.endDate >= current_date()) or (p.valid.endDate is null) ) and
      // p.valid.startDate <= current_date()");
      // Collection collection = find("from Employee p", map);
      // if ((collection != null) && !(collection.isEmpty())) {
      // return (Employee) collection.iterator().next();
      // } else
      // return null;
      Date currentDate = Calendar.getInstance().getTime();
      DefaultQuery query = new DefaultQuery( Employee.class );
      query.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NOT_NULL ) );
      query.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", currentDate, ComparisonOperators.GREATER_OR_EQUAL_TO ) );
      query.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      query.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NULL ) );
      query.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.OR ) );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "assignment", "projectDirector", ComparisonOperators.EQUAL_TO ) );
      query.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      query.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.startDate", currentDate, ComparisonOperators.LESS_OR_EQUAL_TO ) );
      query.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      query.getQueryOrder().addOrderSpecifier( new OrderSpecifier( "partyName", OrderingDirections.Ascending ) );
      RepositoryResultSet<Employee> results = null; // findByQuery( work, query );
      if( results != null && !results.isEmpty() )
         return (Employee) results.iterator().next();
      else
         return null;
   }

   public Employee findProjectAdministrator( UnitOfWork work ) {
      DefaultQuery q = new DefaultQuery( Employee.class );
      q.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "assignment", "projectAdministrator", ComparisonOperators.EQUAL_TO ) );
      q.getQueryOrder().addOrderSpecifier( new OrderSpecifier( "partyName", OrderingDirections.Ascending ) );
      RepositoryResultSet<Employee> result = null; // (RepositoryResultSet<Employee>) findByQuery( work, q );
      if( result != null && !result.isEmpty() )
         return (Employee) result.iterator().next();
      else
         return null;
   }

   public Collection<Boolean> isValidUserDatas( UnitOfWork work, String userName, String password ) {
      Collection<Boolean> results = new HashSet<Boolean>();
      Date currentDate = Calendar.getInstance().getTime();
      DefaultQuery q = new DefaultQuery( Employee.class );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NOT_NULL ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", currentDate, ComparisonOperators.GREATER_OR_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NULL ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.OR ) );
      q.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "systemUser.userName", userName, ComparisonOperators.EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "systemUser.passwrd", password, ComparisonOperators.EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.startDate", currentDate, ComparisonOperators.LESS_OR_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      RepositoryResultSet<Employee> queryResults = null; // (RepositoryResultSet<Employee>) findByQuery( work, q );
      if( queryResults != null && !queryResults.isEmpty() )
         results.add( new Boolean( true ) );
      else
         results.add( new Boolean( false ) );
      return results;
   }

   public Collection<Boolean> isValidInitials( UnitOfWork work, String initials ) {
      Collection<Boolean> results = new HashSet<Boolean>();
      Date currentDate = Calendar.getInstance().getTime();
      DefaultQuery q = new DefaultQuery( Employee.class );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NOT_NULL ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", currentDate, ComparisonOperators.GREATER_OR_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NULL ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.OR ) );
      q.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "initials", initials, ComparisonOperators.EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.startDate", currentDate, ComparisonOperators.LESS_OR_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      RepositoryResultSet<Employee> queryResults = null; // (RepositoryResultSet<Employee>) findByQuery( work, q );
      if( queryResults != null && !queryResults.isEmpty() )
         results.add( new Boolean( true ) );
      else
         results.add( new Boolean( false ) );
      return results;

   }

   public boolean hasActiveEmployeeByAssignment( UnitOfWork work, String assignment ) {
      Date currentDate = Calendar.getInstance().getTime();
      DefaultQuery q = new DefaultQuery( Employee.class );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NOT_NULL ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", currentDate, ComparisonOperators.GREATER_OR_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.endDate", ComparisonOperators.IS_NULL ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.OR ) );
      q.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "assignment", assignment, ComparisonOperators.EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryCondition().addAttributeCondition( new DateAttributeCondition( "valid.startDate", currentDate, ComparisonOperators.LESS_OR_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      q.getQueryOrder().addOrderSpecifier( new OrderSpecifier( "partyName", OrderingDirections.Ascending ) );
      RepositoryResultSet<Employee> queryResults = null; // (RepositoryResultSet<Employee>) findByQuery( work, q );
      if( queryResults != null && !queryResults.isEmpty() )
         return true;
      else
         return false;
   }

   public Party<?> findPartyById( UnitOfWork work, Integer id ) {
      return (Party<?>) findById( work, Party.class, id );
   }

   public RepositoryResultSet<Party> findPartiesByPartyTypeName( UnitOfWork work, String name ) {
      Query q = new DefaultQuery( Party.class );
      q.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "partyType.name", name, ComparisonOperators.EQUAL_TO ) );
      return (RepositoryResultSet<Party>) findByQuery( work, q );
   }

   // PartyRole
   public Party<?> findPartyByPartyRoleId( UnitOfWork work, String id ) {
      return findPartyByPartyRoleId( work, new Integer( id ) );
   }

   public Party<?> findPartyByPartyRoleId( UnitOfWork work, Integer id ) {
      PartyRole role = (PartyRole) findExternalById( work, PartyRole.class, id );
      return role.getParty();
   }

   public Party<?> findOtherPartyByPartyRole( UnitOfWork work, Integer relationshipId, Integer partyRoleId ) {
      DefaultQuery q = new DefaultQuery( PartyRoleType.class );
      q.getQueryCondition().addAttributeCondition( new IntegerAttributeCondition( "partyRelationship.id", relationshipId, ComparisonOperators.EQUAL_TO ) );
      q.getQueryCondition().addAttributeCondition( new IntegerAttributeCondition( "id", partyRoleId, ComparisonOperators.NOT_EQUAL_TO ) );
      q.getQueryCondition().addBooleanOperator( new BooleanOperator( BooleanOperators.AND ) );
      RepositoryResultSet<PersistentObject> roles = findExternalByQuery( work, q );
      if( roles != null && !roles.isEmpty() ){
         return ((PartyRole) roles.iterator().next()).getParty();
      }
      return null;
   }

   public Party<?> findOtherPartyByPartyRole( UnitOfWork work, String relationshipId, String partyRoleId ) {
      return findOtherPartyByPartyRole( work, new Integer( relationshipId ), new Integer( partyRoleId ) );
   }

   protected Object findByIdentityExpression( String identityExpression ) {
      return null;
   }

   public void deleteOrganizationUnit( UnitOfWork work, OrganizationUnit organizationUnit ) {
      delete( work, OrganizationUnit.class, findOrganizationById( work, organizationUnit.getId() ) );
   }

   public RepositoryResultSet<Party<?>> findPartiesByAddressSettlementIdAndZipCodeId( UnitOfWork work, Integer sId, Integer zId ) {
      return null;
   }

   public Party<?> getPartyDataSheetById( UnitOfWork work, Integer integer ) {
      return getPartyByPartyDataSheetId( work, integer );
   }

   public Party<?> getPartyByPartyDataSheetId( UnitOfWork work, Integer integer ) {
      PartyDataSheet<?, ?> sheet = (PartyDataSheet<?, ?>) findExternalById( work, PartyDataSheet.class, integer );
      return sheet.getParty();
   }

   public Party<?> findPartyByAddressId( UnitOfWork work, Integer integer ) {
      return null;
   }

   public Party<?> findPartyByAddress( UnitOfWork work, String emailAddress ) {
      return null;
   }

   @SuppressWarnings( "unchecked" )
   public List<Party> findPartiesByPartyName( UnitOfWork work, String partyName ) {
      List<Party> parties = new ArrayList<Party>();

      Query query = new DefaultQuery( Party.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "partyName.name", partyName, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<Party> results = (RepositoryResultSet<Party>) super.findByQuery( work, query );

      personRepository = applicationContext.getRepository( PersonRepository.class );
      companyRepository = applicationContext.getRepository( CompanyRepository.class );

      for( Party party : results ){
         if( party instanceof Employee ){
            party = findEmployeeById( work, party.getId() );
         }else if( party instanceof Person ){
            party = personRepository.findPersonById( work, party.getId() );
         }else if( party instanceof Company ){
            party = companyRepository.findCompanyById( work, party.getId() );
         }else if( party instanceof Organization ){
            party = findOrganizationById( work, party.getId() );
         }
         parties.add( party );
      }
      return parties;

   }

   // public PurchaserRole findPurchaserRoleById( UnitOfWork work, Integer id ) {
   // PurchaserRole role = (PurchaserRole) findExternalById( work, PurchaserRole.class, id );
   // return role;
   // }
   //
   public Party findPartyByPartySummary( PartySummary partySummary ) {
      Set<PartyIdentifier> identifiers = partySummary.getIdentifiers();
      UnitOfWork work = new DefaultUnitOfWork( true );
      try{
         for( PartyIdentifier identifier : identifiers ){
            Party<?> party = findPartyById( work, new Integer( identifier.getIdentifier() ) );
            if( party != null )
               return party;
         }
      }catch( Exception e ){}finally{
         work.finish();
      }
      return null;
   }
}
