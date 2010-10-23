/**
 * 
 */
package biz.ritter.develop.cobol.builder;

import java.util.Map;

import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;

import biz.ritter.develop.cobol.CorePlugInActivator;


/**
 * @author Nerger
 *
 */
public class COBOLProjectBuilder extends AbstractIncrementalProjectBuilder {

	@Override
	protected void startupOnInitialize() {
		// TODO Check for exist COBOL compiler
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		super.startupOnInitialize();
		try {
		String [] natures = this.getProject().getDescription().getNatureIds();
			for (String nature : natures) {
				System.out.println("  Nature: "+nature);
				IStatus natureStatus = ResourcesPlugin.getWorkspace().validateNatureSet(new String [] {nature});

			      // check the status and decide what to do
			      if (IStatus.OK == natureStatus.getSeverity()) {
			    	  
			      } else {
			    	  System.out.println("BULLSHIT");
			      }
			   }
		}
		catch (CoreException shit) {
			System.err.println(shit.getLocalizedMessage());
		}
	}
	@Override
	protected void clean(IProgressMonitor monitor) throws CoreException {
		// TODO Auto-generated method stub
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		super.clean(monitor);
	}
	@Override
	protected IProject[] obtainInterestingProjects(int kind, Map<String,String> args) {
		// TODO Auto-generated method stub
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		return null;
	}

	@Override
	protected boolean buildResourceDelta(IResourceDelta delta, int kind,
			Map<String, String> args, IProgressMonitor monitor) {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		final IResource resource = delta.getResource();
		return this.buildResource(resource, args, monitor);
	}

	@Override
	protected boolean buildResource(IResource resource,
			Map<String, String> args, IProgressMonitor monitor) {
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
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
