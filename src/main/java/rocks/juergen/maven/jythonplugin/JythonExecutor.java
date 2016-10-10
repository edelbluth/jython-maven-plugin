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

import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Execute Python stuff
 */
final class JythonExecutor {

    /**
     * Execute the stuff with a context map
     *
     * @param executor which executor should be used?
     * @param context the context for the execution
     * @throws IllegalAccessException on an illegal access to the executor class
     * @throws InstantiationException on an instantiation exception with the executor class
     * @throws IOException when something gone wrong with accessing the script
     */
    static void execute(Class<? extends IJythonExecutor> executor, Map<String, String> context) throws IllegalAccessException, InstantiationException, IOException {
        final IJythonExecutor jythonExecutor = executor.newInstance();
        jythonExecutor.setContext(context);
        jythonExecutor.executeJython();
    }

    /**
     * Execute the stuff with vararg key value pairs
     *
     * @param executor which executor should be used?
     * @param contextValues The pairs of context data
     * @throws IllegalAccessException on an illegal access to the executor class
     * @throws InstantiationException on an instantiation exception with the executor class
     * @throws IOException when something gone wrong with accessing the script
     */
    @SafeVarargs
    static void execute(Class<? extends IJythonExecutor> executor, AbstractMap.SimpleEntry<String, String>... contextValues) throws InstantiationException, IllegalAccessException, IOException {
        final HashMap<String, String> context = new HashMap<>();
        for (AbstractMap.SimpleEntry<String, String> entry : contextValues) {
            context.put(entry.getKey(), entry.getValue());
        }
        execute(executor, context);
    }

}
