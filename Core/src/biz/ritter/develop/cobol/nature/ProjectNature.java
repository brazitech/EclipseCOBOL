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
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		AbstractIncrementalProjectBuilder.addBuilder(this.getProject(), CODEConstants.CODE_FULLQUALIFIED_BUILDER_ID, true, null);
	}

	@Override
	public void deconfigure() throws CoreException {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		AbstractIncrementalProjectBuilder.removeBuilder(this.getProject(), CODEConstants.CODE_FULLQUALIFIED_BUILDER_ID, true, null);
	}

	@Override
	public IProject getProject() {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		return this.cobolProject;
	}

	@Override
	public void setProject (final IProject project) {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		this.cobolProject = project;
	}

}
