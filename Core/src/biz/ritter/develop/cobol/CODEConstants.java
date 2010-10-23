/**
 * 
 */
package biz.ritter.develop.cobol;

/**
 * @author Nerger
 *
 */
public interface CODEConstants {

	/**
	 * Public ID for COBOL nature.
	 */
	public static final String CODE_FULLQUALIFIED_NATURE_ID = CODEConstants.CODE_CORE_ID + "." + "CODENature";
	/**
	 * Public plugin ID for COBOL core plugin.
	 */
	public static final String CODE_CORE_ID = "CODE_Core";
	
	/**
	 * Public ID for COBOL builder.
	 */
	public static final String CODE_FULLQUALIFIED_BUILDER_ID = CODE_CORE_ID + "." + "CODE_Builder";
}
