package com.processpuzzle.application.configuration.control;

public class SkinDescriptor {
   public static final String BASE_PATH = "Commons/Skins";
   public static final String STYLES_FOLDER = "/Styles";
   public static final String IMAGES_FOLDER = "/Images";
   public static final String LAYOUT_FOLDER = "/Layout";
   private String skinName;
   private String skinPath;
   
   public SkinDescriptor( String skinName, String skinPath ) {
      this.skinName = skinName;
      this.skinPath = skinPath;
   }

   public String getSkinName() { return skinName; }
   public String getStylesPath() { return BASE_PATH + "/" + skinPath + STYLES_FOLDER; }
   public String getImagesPath() { return BASE_PATH + "/" + skinPath + IMAGES_FOLDER; }
   public String getLayoutPath() { return BASE_PATH + "/" + skinPath + LAYOUT_FOLDER; }
}
