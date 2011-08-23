package com.processpuzzle.internalization.domain;


public class NoneExistingResourceKeyException extends InternalizationException {
   private static final long serialVersionUID = 7380209350697785819L;
   protected static String defaultMessagePattern = "Resource bundle files: '''{0}'''.* doesn't contains value for key: '''{1}''' in locale: '''{2}'''";

   protected NoneExistingResourceKeyException( String key, String resourcePath, ProcessPuzzleLocale locale ) {
      //Note: In this case we can't use ExceptionHelper because it calls ResourceBundle which can throw
      // 'NoneExistingResourceKeyException' again. This can cause stack overflow!
      super( defineMessage( key, resourcePath, locale) );
   }

   protected NoneExistingResourceKeyException( String key ) {
      //Note: In this case we can't use ExceptionHelper because it calls ResourceBundle which can throw
      // 'NoneExistingResourceKeyException' again. This can cause stack overflow!
      super( defineMessage(key) );
   }

   protected static Object[] defineMessage( String key, String resourcePath, ProcessPuzzleLocale locale ) {
      formatPattern = "Resource bundle files: '''{0}'''.* doesn't contains value for key: '''{1}''' in locale: '''{2}'''";
      Object[] arguments = {key, resourcePath, locale.getLanguage() + ", " + locale.getCountry() + ", " + locale.getVariant() };
      return arguments;
   }

   protected static Object[] defineMessage( String key ) {
      formatPattern = "Resource bundle files doesn't contains value for key: '''{0}'''";
      Object[] arguments = {key};
      return arguments;
   }
}
