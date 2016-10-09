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

import org.python.util.PythonInterpreter;

import java.io.File;
import java.util.Map;

class JythonFileExcecutor implements IJythonExecutor {

    static final String SCRIPT_FILE_INDEX = "__SCRIPT_FILE__";

    private Map<String, String> context;

    public void setContext(Map<String, String> context) {
        this.context = context;
    }

    public void executeJython() {
        final String scriptFile = context.get(SCRIPT_FILE_INDEX);
        final String dir = new File(scriptFile).getParent();
        final PythonInterpreter interpreter = JythonInterpreterToolbox.connectedInterpreterFactory(dir);
        JythonInterpreterToolbox.setContext(interpreter, context);
        interpreter.execfile(scriptFile);
        JythonInterpreterToolbox.cleanup(interpreter);
    }

}
