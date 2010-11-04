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
package biz.ritter.develop.cobol.prefs;

import biz.ritter.develop.cobol.CorePlugInActivator;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class COBOLPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public COBOLPreferencePage() {
		super(GRID);
		setMessage("CODE Preferences");
		setTitle("COBOL");
		setPreferenceStore(CorePlugInActivator.getDefault().getPreferenceStore());
		setDescription("COBOL Development Environment preferences pages");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		addField(new DirectoryFieldEditor(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_PATH, 
				"COBOL compiler &directory", getFieldEditorParent()));
		{
		  StringFieldEditor stringFieldEditor = new StringFieldEditor(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_EXECUTABLE, "COBOL &compiler name", getFieldEditorParent());
		  stringFieldEditor.setEmptyStringAllowed(false);
		  addField(
		  	stringFieldEditor);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}