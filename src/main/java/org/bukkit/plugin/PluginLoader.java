package org.bukkit.plugin;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;

/**
 * Represents a plugin loader, which handles direct access to specific types
 * of plugins
 */
public interface PluginLoader {

    /**
     * Loads the plugin contained in the specified file
     *
     * @param file File to attempt to load
     * @return Plugin that was contained in the specified file, or null if
     *         unsuccessful
     * @throws InvalidPluginException Thrown when the specified file is not a plugin
     * @throws InvalidDescriptionException If the plugin description file was invalid
     * @throws UnknownDependencyException If a required dependency could not be found
     */
    public Plugin loadPlugin(File file) throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException;

    /**
     * Loads the plugin contained in the specified file
     *
     * @deprecated SoftDependencies are always ignored
     * @param file File to attempt to load
     * @param ignoreSoftDependencies Loader will ignore soft dependencies if this flag is set to true
     * @return Plugin that was contained in the specified file, or null if
     *         unsuccessful
     * @throws InvalidPluginException Thrown when the specified file is not a plugin
     * @throws InvalidDescriptionException If the plugin description file was invalid
     * @throws UnknownDependencyException If a required dependency could not be found
     */
    @Deprecated
    public Plugin loadPlugin(File file, boolean ignoreSoftDependencies) throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException;

    /**
     * Loads a PluginDescriptionFile from the specified file
     *
     * @param file File to attempt to load from
     * @return A new PluginDescriptionFile loaded from the plugin.yml in the specified file
     * @throws InvalidPluginException If when the specified file does not contain a plugin description file
     * @throws InvalidDescriptionException If the plugin description file could not be created
     */
    public PluginDescriptionFile getPluginDescription(File file) throws InvalidPluginException, InvalidDescriptionException;

    /**
     * Returns a list of all filename filters expected by this PluginLoader
     *
     * @return The filters
     */
    public Pattern[] getPluginFileFilters();

    /**
     * Creates and returns an event executor
     *
     * @param type Type of the event executor to create
     * @param listener the object that will handle the eventual call back
     * @return The new executor
     * @deprecated see PluginLoader#createRegisteredListeners
     */
    @Deprecated
    public EventExecutor createExecutor(Event.Type type, Listener listener);

    /**
     * Creates and returns registered listeners for the event classes used in this listener
     *
     * @param listener The object that will handle the eventual call back
     * @param plugin The plugin to use when creating registered listeners
     * @return The registered listeners.
     */
    public Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(Listener listener, Plugin plugin);

    /**
     * Enables the specified plugin
     * <p />
     * Attempting to enable a plugin that is already enabled will have no effect
     *
     * @param plugin Plugin to enable
     */
    public void enablePlugin(Plugin plugin);

    /**
     * Disables the specified plugin
     * <p />
     * Attempting to disable a plugin that is not enabled will have no effect
     *
     * @param plugin Plugin to disable
     */
    public void disablePlugin(Plugin plugin);
}
