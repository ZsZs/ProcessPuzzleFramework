package com.processpuzzle.application.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.artifact.domain.ArtifactFolderRepository;
import com.processpuzzle.artifact.domain.RootArtifactFolder;
import com.processpuzzle.business.definition.domain.SystemArtifact;
import com.processpuzzle.party.partytype.domain.PartyTypeRepository;

public class DomainComponentsApplicationTest extends NotInstalledApplicationTest {
   private ArtifactFolderRepository folderRepository;

   @Ignore
   @Test
   public void install_ShouldLoadMultipleBusinessDefintions() throws ApplicationException {
      //EXERCISE:
      sut.install();
      folderRepository = sut.getContext().getRepository( ArtifactFolderRepository.class );
      
      //VERIFY:
      PartyTypeRepository partyTypeRepository = sut.getContext().getRepository( PartyTypeRepository.class );
      assertThat( "'NonProfitOrganization' is defined in a 'TestBusinessDefinition_Two.xml'. See the default_configuraiton.xml.", 
            partyTypeRepository.findByName( "NonProfitOrganization" ), notNullValue() );
   
      assertThat( thereIsOneRootArtifactFolder(), is( true ));
      assertThat( thereIsOneArtifactsFolder(), is( true ));
      
      //TEARDOWN:
      sut.stop();
      sut.unInstall();
   }
   
   private boolean thereIsOneArtifactsFolder() {
      if( folderRepository.findByPath( SystemArtifact.ARTIFACTS_FOLDER.getPath() ) != null ) return true;
      else return false;
   }
   
   private boolean thereIsOneRootArtifactFolder() {
      ArtifactFolderRepository folderRepository = sut.getContext().getRepository( ArtifactFolderRepository.class );
      if( folderRepository.findByName( RootArtifactFolder.ROOT_ARTIFACT_FOLDER_NAME ) != null ) return true;
      else return false;
   }
}
