/**
 * 
 */
package biz.ritter.develop.cobol.nature;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

import biz.ritter.develop.cobol.CODEConstants;
import biz.ritter.develop.cobol.builder.AbstractIncrementalProjectBuilder;

/**
 * @author Nerger
 *
 */
public class ProjectNature implements IProjectNature {
	
	private IProject cobolProject;

	/**
	 * 
	 */
	public ProjectNature() {
	}

	@Override
	public void configure() throws CoreException {
		AbstractIncrementalProjectBuilder.addBuilder(this.getProject(), CODEConstants.CODE_BUILDER_ID, true, null);
	}

	@Override
	public void deconfigure() throws CoreException {
		AbstractIncrementalProjectBuilder.removeBuilder(this.getProject(), CODEConstants.CODE_BUILDER_ID, true, null);
	}

	@Override
	public IProject getProject() {
		return this.cobolProject;
	}

	@Override
	public void setProject (final IProject project) {
		this.cobolProject = project;
		
	}
	
}
