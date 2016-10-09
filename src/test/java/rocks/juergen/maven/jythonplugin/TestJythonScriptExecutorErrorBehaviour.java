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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.python.core.PyException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class TestJythonScriptExecutorErrorBehaviour extends AbstractStdOutErrTest {

    private final String scriptLine;

    public TestJythonScriptExecutorErrorBehaviour(final String scriptLine) {
        this.scriptLine = scriptLine;
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static List<String[]> getParameters() {
        final ArrayList<String[]> parameters = new ArrayList<>();
        parameters.add(new String[]{"bla_print('Hello, World!') # bla_print is undefined!"});
        parameters.add(new String[]{"totally('"});
        parameters.add(new String[]{"for x of range(1,999):"});
        parameters.add(new String[]{"for x in range(1,999):"});
        parameters.add(new String[]{"for x in range(1,999)"});
        return parameters;
    }

    @Test(expected = PyException.class)
    public void testErrorBehaviour() throws IOException {
        final HashMap<String, String> context = new HashMap<>();
        context.put(JythonScriptExecutor.INLINE_SCRIPT_INDEX, scriptLine);
        final JythonScriptExecutor executor = new JythonScriptExecutor();
        executor.setContext(context);
        executor.executeJython();
    }

    @Test
    public void testStdOutErrBehaviour() throws IOException {
        final HashMap<String, String> context = new HashMap<>();
        context.put(JythonScriptExecutor.INLINE_SCRIPT_INDEX, scriptLine);
        final JythonScriptExecutor executor = new JythonScriptExecutor();
        executor.setContext(context);
        try {
            executor.executeJython();
        }
        catch (PyException e) {
            assertNotNull(e);
        }
        assertEquals("", getStdOutErr().getLocalStdOut().toString().trim());
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
    }

}
