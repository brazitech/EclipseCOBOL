/**
 * Copyright 2010 Sebastian Nerger
 *
 * Lizenziert unter der EUPL, nur Version 1.1 ("Lizenz");
 * 
 * Sie dürfen dieses Werk ausschließlich gemäß
 * dieser Lizenz nutzen.
 * Eine Kopie der Lizenz finden Sie hier:
 *
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Sofern nicht durch anwendbare Rechtsvorschriften
 * gefordert oder in schriftlicher Form vereinbart, wird
 * die unter der Lizenz verbreitete Software "so wie sie
 * ist", OHNE JEGLICHE GEWÄHRLEISTUNG ODER BEDINGUNGEN -
 * ausdrücklich oder stillschweigend - verbreitet.
 * Die sprachspezifischen Genehmigungen und Beschränkungen
 * unter der Lizenz sind dem Lizenztext zu entnehmen.
 */
package biz.ritter.develop.cobol.editor;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * COBOLWordDetector determines whether a given character is valid as part
 * of a COBOL word in the current context.
 */
public class COBOLWordDetector implements IWordDetector {
  
  /*
   * COBOL word can contain same as JavaIdentifiertPart, except '_' is
   * replaced by '-'.
   */
  public boolean isWordPart( final char character ) {
    switch (character) {
    case '_' : return false;
    case '-' : return true;
    default  : return Character.isJavaIdentifierPart( character );
    }
  }

  /*
   * COBOL word can contain same as JavaIdentifiertPart, except '_' is
   * replaced by '-'.
   */
  public boolean isWordStart( final char character ) {
    switch (character) {
    case '_' : return false;
    case '-' : return true;
    default  : return Character.isJavaIdentifierStart( character );
    }
  }    
}
