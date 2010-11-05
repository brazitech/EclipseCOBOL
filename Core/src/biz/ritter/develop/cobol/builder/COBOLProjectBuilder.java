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

import biz.ritter.develop.cobol.CorePlugInActivator;
import biz.ritter.develop.cobol.prefs.COBOLPreferenceConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * @author Nerger
 * 
 */
public class COBOLProjectBuilder extends AbstractIncrementalProjectBuilder {
  
  protected String fullQualifiedCobolCompilerExecutable = null;
  protected String cobolCompilerPath = null;
  
  @Override
  protected void startupOnInitialize() {
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
  protected boolean buildResource(IResource resource, Map<String, String> args,
      IProgressMonitor monitor) {
    // TODO Check for exist COBOL compiler
    
    if (null == this.fullQualifiedCobolCompilerExecutable) {
      this.fullQualifiedCobolCompilerExecutable = 
        CorePlugInActivator.getDefault().getPreferenceStore().getString(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_PATH)
       +File.separatorChar
       +CorePlugInActivator.getDefault().getPreferenceStore().getString(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_EXECUTABLE);
      this.cobolCompilerPath = CorePlugInActivator.getDefault().getPreferenceStore().getString(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_PATH);
    }
    
    // FIXME COBOL Source file types intend hard codes extensions
    final String qualifiedPath = resource.getLocation().toOSString();
    if ("cbl".equals(resource.getFileExtension())
        || "cob".equals(resource.getFileExtension())) {
      try {
        ProcessBuilder pb = new ProcessBuilder(fullQualifiedCobolCompilerExecutable, "", qualifiedPath);
        Map<String, String> env = pb.environment();
        env.put("COBCPY", "HERE");
        env.put("PATH", env.get("PATH")+File.pathSeparatorChar+this.cobolCompilerPath);
        pb.directory(new File(this.cobolCompilerPath));
        Process p = pb.start();
        BufferedReader compilerNormalOutput = new BufferedReader (new InputStreamReader(p.getInputStream()));
        BufferedReader compilerErrorOutput = new BufferedReader (new InputStreamReader(p.getErrorStream()));
        final StringBuilder normalOutput = new StringBuilder();
        final StringBuilder errorOutput = new StringBuilder();
        final int RC = p.exitValue();
        String s;
        while ((s = compilerNormalOutput.readLine()) != null) {
          normalOutput.append(s);
        }
        while ((s = compilerErrorOutput.readLine()) != null) {
          errorOutput.append(s).append(System.getProperty("line.separator"));
        }
        if (0 == RC) {
          System.out.println(normalOutput.toString());
        }
        else {
          System.err.println(errorOutput.toString());
        }
        
      }
      catch (IOException shit) {
        System.out.println("cannot call cobol compiler for " + qualifiedPath + " in ["+ this.getClass().toString() + "]");
        shit.printStackTrace();
      }
    }
    else {
      // Nothing to do
      //System.out.println("call NO cobol compiler for " + qualifiedPath + " in [" + this.getClass().toString() + "]");
    }
    
    return true;
  }
  
}
