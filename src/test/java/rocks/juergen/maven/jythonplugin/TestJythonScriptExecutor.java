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

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

public class TestJythonScriptExecutor extends AbstractStdOutErrTest {

    @Test
    public void testPythonHelloWorld() throws IOException {
        final String pythonScript = "print 'Hello, World!'";
        final HashMap<String, String> context = new HashMap<>();
        context.put(JythonScriptExecutor.INLINE_SCRIPT_INDEX, pythonScript);
        final JythonScriptExecutor executor = new JythonScriptExecutor();
        executor.setContext(context);
        executor.executeJython();
        assertEquals("Hello, World!", getStdOutErr().getLocalStdOut().toString().trim());
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
    }

}
