package rocks.juergen.maven.jythonplugin;

/*
 * Copyright 2016 Juergen Edelbluth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;

/**
 * Helper for execution Jython from a Mojo
 */
abstract class AbstractMojoExecution extends AbstractMojo {

    static final String BASE_DIR_KEY = "MVN_PROJECT_BASE_DIR";

    /**
     * Execute Jython
     *
     * @param key Key for basic configuration (Script, Filename, etc.)
     * @param value Value for basic configuration
     * @param executor Executor class
     * @throws MojoExecutionException when something goes wrong
     */
    @SafeVarargs
    final void execute(final String key, final String value, final Class<? extends IJythonExecutor> executor, final AbstractMap.SimpleEntry<String, String>... extraContext) throws MojoExecutionException {
        final HashMap<String, String> executionContext = new HashMap<>();
        // Provide some things from the maven session
        for (final AbstractMap.SimpleEntry<String, String> entry : extraContext) {
            executionContext.put(entry.getKey(), entry.getValue());
        }
        // This is a must...
        executionContext.put(key, value);
        try {
            JythonExecutor.execute(
                    executor,
                    executionContext
            );
        } catch (InstantiationException | IllegalAccessException | IOException e) {
            throw new MojoExecutionException("Execution failed", e);
        }
    }

}
