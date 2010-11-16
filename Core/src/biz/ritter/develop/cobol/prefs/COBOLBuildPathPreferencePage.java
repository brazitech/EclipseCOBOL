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
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;

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

public class COBOLBuildPathPreferencePage
	extends PreferencePage
	implements IWorkbenchPreferencePage {

	public COBOLBuildPathPreferencePage() {
		super();
		setMessage("COBOL Build Path");
		setTitle("COBOL");
		setPreferenceStore(CorePlugInActivator.getDefault().getPreferenceStore());
		setDescription("Define COBOL build paths and environment.");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

  @Override
  protected Control createContents(Composite parent) {
    // TODO Auto-generated method stub
    Composite pane = new Composite(parent,SWT.BORDER_SOLID);
    RowLayout rl_pane = new RowLayout(SWT.HORIZONTAL);
    rl_pane.fill = true;
    pane.setLayout(rl_pane);
    
    Composite composite_1 = new Composite(pane, SWT.NONE);
    RowLayout rl_composite_1 = new RowLayout(SWT.HORIZONTAL);
    rl_composite_1.fill = true;
    composite_1.setLayout(rl_composite_1);
    
    Composite composite = new Composite(pane, SWT.NONE);
    composite.setLayout(new GridLayout(1, true));
    
    Button button = new Button(composite, SWT.NONE);
    button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    button.setText("&New");
    
    Button button_1 = new Button(composite, SWT.NONE);
    button_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    button_1.setText("&Edit");
    
    Button button_2 = new Button(composite, SWT.NONE);
    button_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    button_2.setText("&Remove");
    return pane;
  }
}