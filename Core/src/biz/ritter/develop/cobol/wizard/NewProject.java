/**
 * 
 */
package biz.ritter.develop.cobol.wizard;

import java.net.URI;
import java.util.LinkedList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import biz.ritter.develop.cobol.CorePlugInActivator;
import biz.ritter.develop.cobol.core.COBOLProjectSupport;


/**
 * @author Nerger
 *
 */
public class NewProject extends Wizard implements INewWizard, IExecutableExtension {
	
	public LinkedList<IWizardPage> pages = new LinkedList<IWizardPage>();
	
	public NewProject() {
		pages.add(new NewProjectPage());
	}

	@Override
	public void addPages() {
		super.addPages();
		// Add all wizard pages
		for (IWizardPage page : pages) {
			this.addPage(page);
		}
	}
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	@Override
	public boolean performCancel() {
		return super.performCancel();
	}	
	
	@Override
	public boolean performFinish() {
		try {
			// create a new COBOL project...
			final NewProjectPage newProjectPage = (NewProjectPage)this.pages.get(0);
		    final String name = newProjectPage.getProjectName();
		    URI location = null;
		    if (!newProjectPage.isUseDefaultLocation()) {
		        location = new URI(newProjectPage.getLocation());
		    } // else location == null
		 
		    COBOLProjectSupport.createProject(name, location);
		}
		catch (Throwable t) {
			CorePlugInActivator.getDefault().getLog().log(new Status(Status.ERROR, CorePlugInActivator.PLUGIN_ID, t.getLocalizedMessage(),t));
			return false;
		}
		return true;
	}

	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
	};

	public void dispose() {
		super.dispose();
	}

	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		
	};
	
}
