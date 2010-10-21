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
import org.eclipse.core.runtime.Status;


/**
 * @author Ritter
 *
 */
public class COBOLProjectBuilder extends AbstractIncrementalProjectBuilder {

	@Override
	protected void startupOnInitialize() {
		// TODO Check for exist COBOL compiler
		super.startupOnInitialize();
BuilderPlugInActivator.getDefault().getLog().log(new Status(Status.WARNING, BuilderPlugInActivator.PLUGIN_ID, "TODO in class COBOLProjectBuilder!"));		
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
BuilderPlugInActivator.getDefault().getLog().log(new Status(Status.WARNING, BuilderPlugInActivator.PLUGIN_ID, "TODO in class COBOLProjectBuilder!"));		
		if ("cbl".equals (resource.getFileExtension()) ||
			"cob".equals (resource.getFileExtension())) {
final String qualifiedPath = resource.getFullPath().toOSString();
			System.out.println("call cobol compiler for "+qualifiedPath +" in ["+this.getClass().toString()+"]");
		}

		
		return true;
	}


}
