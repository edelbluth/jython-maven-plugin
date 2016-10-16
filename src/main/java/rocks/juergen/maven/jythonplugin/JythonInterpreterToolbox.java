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

import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import java.util.Map;

/**
 * Create Jython Interpreters and provide some shortcuts for their handling
 */
final class JythonInterpreterToolbox {

    /**
     * Internal helper for creating a connected interpreter
     *
     * @param systemState System State
     * @return a connected interpreter
     */
    private static PythonInterpreter createConnectedInterpreter(final PySystemState systemState) {
        final PythonInterpreter interpreter = new PythonInterpreter(null, systemState);
        interpreter.setIn(System.in);
        interpreter.setOut(System.out);
        interpreter.setErr(System.err);
        return interpreter;
    }

    /**
     * Generate a new Python Interpreter and connect standard input, output and error
     *
     * @return a connected interpreter
     */
    static PythonInterpreter connectedInterpreterFactory() {
        return createConnectedInterpreter(new PySystemState());
    }

    /**
     * Generate a new Python Interpreter, connected to the standard input, output and error, with an extended python path
     *
     * @param pythonPathExtension extensions to the python path
     * @return a connected interpreter with extended python path
     */
    static PythonInterpreter connectedInterpreterFactory(final String... pythonPathExtension) {
        final PySystemState pySysStat = new PySystemState();
        for (final String extension : pythonPathExtension) {
            if (extension != null) {
                final String ex = extension.trim();
                if (ex.length() > 0) {
                    pySysStat.path.append(new PyString(ex));
                }
            }
        }
        return createConnectedInterpreter(pySysStat);
    }

    /**
     * Cleanup Helper for interpreters generated in this factory
     *
     * @param interpreter the interpreter to be cleaned up
     */
    static void cleanup(final PythonInterpreter interpreter) {
        interpreter.cleanup();
        interpreter.close();
    }

    /**
     * Set context parameters to the python interpreter
     *
     * @param interpreter the interpreter to configure
     * @param context the context data to set
     */
    static void setContext(final PythonInterpreter interpreter, final Map<String, String> context) {
        for (Map.Entry<String, String> stringStringEntry : context.entrySet()) {
            interpreter.set(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
    }

}
