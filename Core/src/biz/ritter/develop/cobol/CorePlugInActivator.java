package biz.ritter.develop.cobol;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import biz.ritter.develop.cobol.builder.COBOLProjectBuilder;

/**
 * The activator class controls the plug-in life cycle
 */
public class CorePlugInActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = CODEConstants.CODE_CORE_ID;

	// The shared instance
	private static CorePlugInActivator plugin;
	
	/**
	 * The constructor
	 */
	public CorePlugInActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static CorePlugInActivator getDefault() {
		return plugin;
	}

}
