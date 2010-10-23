/**
 * 
 */
package biz.ritter.develop.cobol.builder;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;


/**
 * @author Nerger
 *
 */
public class COBOLProjectBuilder extends AbstractIncrementalProjectBuilder {

	@Override
	protected void startupOnInitialize() {
		// TODO Check for exist COBOL compiler
		super.startupOnInitialize();
System.out.println(new Exception().getStackTrace()[0].getMethodName());
	}
	@Override
	protected void clean(IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub
		super.clean(monitor);
	}
	@Override
	protected IProject[] obtainInterestingProjects(int kind, Map<String,String> args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean buildResourceDelta(IResourceDelta delta, int kind,
			Map<String, String> args, IProgressMonitor monitor) {
		final IResource resource = delta.getResource();
		return this.buildResource(resource, args, monitor);
	}

	@Override
	protected boolean buildResource(IResource resource,
			Map<String, String> args, IProgressMonitor monitor) {
		// FIXME COBOL Source file types intend hard codes extensions
		final String qualifiedPath = resource.getFullPath().toOSString();
		if ("cbl".equals (resource.getFileExtension()) ||
			"cob".equals (resource.getFileExtension())) {
			System.out.println("call cobol compiler for "+qualifiedPath +" in ["+this.getClass().toString()+"]");
		}
		else {
		  System.out.println("call NO cobol compiler for "+qualifiedPath +" in ["+this.getClass().toString()+"]");
		}
		
		return true;
	}


}
