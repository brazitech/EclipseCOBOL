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
package biz.ritter.develop.cobol.core;

import java.net.URI;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import biz.ritter.develop.cobol.builder.AbstractIncrementalProjectBuilder;
import biz.ritter.develop.cobol.internal.CODEConstants;

/**
 * @author Nerger
 * @see http
 *      ://cvalcarcel.wordpress.com/2009/07/26/writing-an
 *      -eclipse
 *      -plug-in-part-4-create-a-custom-project-in-eclipse
 *      -new-project-wizard-the-behavior/
 */
public class COBOLProjectSupport {
  
  /**
   * For this marvelous project we need to: - create the
   * default Eclipse project - add the custom project nature
   * - create the folder structure
   * 
   * @param projectName
   * @param location
   * @param natureId
   * @return
   */
  public static IProject createProject(String projectName, URI location) {
    Assert.isNotNull(projectName);
    Assert.isTrue(projectName.trim().length() > 0);
    
    IProject project = createBaseProject(projectName, location);
    try {
      // add default source paths
      String[] paths = { "src/cpy", "src/cbl" }; //$NON-NLS-1$ //$NON-NLS-2$
      addToProjectStructure(project, paths);
      
      // add CODE Builder
      addBuilder(project, CODEConstants.CODE_FULLQUALIFIED_BUILDER_ID);
      // add CODE Nature
      addNature(project, CODEConstants.CODE_FULLQUALIFIED_NATURE_ID);
    }
    catch (CoreException e) {
      e.printStackTrace();
      project = null;
    }
    
    return project;
  }
  
  private static void addBuilder(IProject project, String codeBuilderId)
      throws CoreException {
    System.out.println(new Exception().getStackTrace()[0].getMethodName());
    AbstractIncrementalProjectBuilder.addBuilder(project,
        CODEConstants.CODE_FULLQUALIFIED_BUILDER_ID, true, null);
  }
  
  /**
   * Just do the basics: create a basic project.
   * 
   * @param location
   * @param projectName
   */
  private static IProject createBaseProject(String projectName, URI location) {
    // it is acceptable to use the ResourcesPlugin class
    IProject newProject = ResourcesPlugin.getWorkspace().getRoot()
        .getProject(projectName);
    
    if (!newProject.exists()) {
      URI projectLocation = location;
      IProjectDescription desc = newProject.getWorkspace()
          .newProjectDescription(newProject.getName());
      if (location != null
          && ResourcesPlugin.getWorkspace().getRoot().getLocationURI()
              .equals(location)) {
        projectLocation = null;
      }
      
      desc.setLocationURI(projectLocation);
      try {
        newProject.create(desc, null);
        if (!newProject.isOpen()) {
          newProject.open(null);
        }
      }
      catch (CoreException e) {
        e.printStackTrace();
      }
    }
    
    return newProject;
  }
  
  private static void createFolder(IFolder folder) throws CoreException {
    IContainer parent = folder.getParent();
    if (parent instanceof IFolder) {
      createFolder((IFolder) parent);
    }
    if (!folder.exists()) {
      folder.create(false, true, null);
    }
  }
  
  /**
   * Create a folder structure with a parent root, overlay,
   * and a few child folders.
   * 
   * @param newProject
   * @param paths
   * @throws CoreException
   */
  private static void addToProjectStructure(IProject newProject, String[] paths)
      throws CoreException {
    for (String path : paths) {
      IFolder etcFolders = newProject.getFolder(path);
      createFolder(etcFolders);
    }
  }
  
  private static void addNature(IProject project, final String NATURE_ID)
      throws CoreException {
    if (!project.hasNature(NATURE_ID)) {
      IProjectDescription description = project.getDescription();
      String[] prevNatures = description.getNatureIds();
      String[] newNatures = new String[prevNatures.length + 1];
      System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
      newNatures[prevNatures.length] = NATURE_ID;
      description.setNatureIds(newNatures);
      
      IProgressMonitor monitor = null;
      project
          .setDescription(description, IProject.AVOID_NATURE_CONFIG, monitor);
    }
  }
}
