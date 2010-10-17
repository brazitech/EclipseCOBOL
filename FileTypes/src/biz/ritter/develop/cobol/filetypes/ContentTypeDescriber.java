/**
 * 
 */
package biz.ritter.develop.cobol.filetypes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;

/**
 * @author Ritter
 *
 */
public class ContentTypeDescriber implements ITextContentDescriber{

	/**
	 * 
	 */
	public ContentTypeDescriber() {
	}

	@Override
	public int describe (final InputStream contents, 
			             final IContentDescription description)
			throws IOException {
		return this.describe (new InputStreamReader(contents), description);
	}

	@Override
	public QualifiedName[] getSupportedOptions() {
		return null;
	}

	@Override
	public int describe (final Reader contents, 
			             final IContentDescription description)
			throws IOException {
		return INDETERMINATE;
	}

}
