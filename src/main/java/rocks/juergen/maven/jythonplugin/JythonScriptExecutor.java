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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * A simple Script executor, using a simple interpreter
 */
class JythonScriptExecutor implements IJythonExecutor {

    static final String INLINE_SCRIPT_INDEX = "__INLINE_SCRIPT__";

    private Map<String, String> context;

    public void setContext(Map<String, String> context) {
        this.context = context;
    }

    public void executeJython() throws IOException {
        final PythonInterpreter interpreter = JythonInterpreterToolbox.connectedInterpreterFactory();
        final String code = context.get(INLINE_SCRIPT_INDEX);
        JythonInterpreterToolbox.setContext(interpreter, context);
        final ByteArrayInputStream codeInputStream = new ByteArrayInputStream(code.getBytes());
        interpreter.execfile(codeInputStream);
        JythonInterpreterToolbox.cleanup(interpreter);
    }

}
