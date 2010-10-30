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
package biz.ritter.develop.cobol.editor;

import java.util.ResourceBundle;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.TextOperationAction;

import biz.ritter.develop.cobol.EditorActivator;


/** Implements COBOLSourceEditor.
 * 
 * @author Leif Frenzel
 */
public class COBOLSourceEditor extends TextEditor {

  protected void initializeEditor() {
    super.initializeEditor();
    
    // Our own SourceViewer for more functions...  
    setSourceViewerConfiguration( new COBOLSourceViewerConfiguration() );
  }


  protected void createActions() {
    super.createActions();
    
    // we want a Content Assist,
    IAction action = createAction();
    String actionId = ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS;
    action.setActionDefinitionId( actionId );
    setAction( "ContentAssistProposal", action );
  }


  private IAction createAction() {
    ResourceBundle bundle = EditorActivator.getDefault().getResourceBundle();
    return new TextOperationAction( bundle,
                                    "ContentAssistProposal.", 
                                    this, 
                                    ISourceViewer.CONTENTASSIST_PROPOSALS );
  }  
}
