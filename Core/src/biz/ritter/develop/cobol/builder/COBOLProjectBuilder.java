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
package biz.ritter.develop.cobol.builder;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

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
      String[] natures = this.getProject().getDescription().getNatureIds();
      for (String nature : natures) {
        System.out.println("  Nature: " + nature);
        IStatus natureStatus = ResourcesPlugin.getWorkspace()
            .validateNatureSet(new String[] { nature });
        
        // check the status and decide what to do
        if (IStatus.OK == natureStatus.getSeverity()) {
          
        }
        else {
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
  protected IProject[] obtainInterestingProjects(int kind,
      Map<String, String> args) {
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
  protected boolean buildResource(IResource resource, Map<String, String> args,
      IProgressMonitor monitor) {
    System.out.println(new Exception().getStackTrace()[0].getMethodName());
    // FIXME COBOL Source file types intend hard codes
    // extensions
    final String qualifiedPath = resource.getFullPath().toOSString();
    if ("cbl".equals(resource.getFileExtension())
        || "cob".equals(resource.getFileExtension())) {
      System.out.println("call cobol compiler for " + qualifiedPath + " in ["
          + this.getClass().toString() + "]");
    }
    else {
      System.out.println("call NO cobol compiler for " + qualifiedPath
          + " in [" + this.getClass().toString() + "]");
    }
    
    return true;
  }
  
}
