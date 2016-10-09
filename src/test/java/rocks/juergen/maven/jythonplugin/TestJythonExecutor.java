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
import java.util.AbstractMap;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static rocks.juergen.maven.jythonplugin.JythonExecutor.execute;

public class TestJythonExecutor extends AbstractStdOutErrTest {

    @Test
    public void testScriptExecutorWithContext() throws InstantiationException, IllegalAccessException, IOException {
        final String pythonScript = "print 'Hello, World!'";
        final HashMap<String, String> context = new HashMap<>();
        context.put(JythonScriptExecutor.INLINE_SCRIPT_INDEX, pythonScript);
        execute(JythonScriptExecutor.class, context);
        assertEquals("Hello, World!", getStdOutErr().getLocalStdOut().toString().trim());
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
    }

    @Test
    public void testScriptExecutorWithVarargs() throws IllegalAccessException, InstantiationException, IOException {
        final String pythonScript = "print 'Hello, World!'";
        execute(
                JythonScriptExecutor.class,
                new AbstractMap.SimpleEntry<>(JythonScriptExecutor.INLINE_SCRIPT_INDEX, pythonScript)
        );
        assertEquals("Hello, World!", getStdOutErr().getLocalStdOut().toString().trim());
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
    }

    @Test
    public void testFileExcecutorWithContext() throws InstantiationException, IllegalAccessException, IOException {
        final HashMap<String, String> context = new HashMap<>();
        context.put(JythonFileExcecutor.SCRIPT_FILE_INDEX, TestJythonExecutor.class.getResource("helloworld.py").getFile());
        execute(JythonFileExcecutor.class, context);
        assertEquals("Hello, World - from file!", getStdOutErr().getLocalStdOut().toString().trim());
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
    }

    @Test
    public void testFileExcecutorWithVarargs() throws InstantiationException, IllegalAccessException, IOException {
        execute(
                JythonFileExcecutor.class,
                new AbstractMap.SimpleEntry<>(JythonFileExcecutor.SCRIPT_FILE_INDEX, TestJythonExecutor.class.getResource("helloworld.py").getFile())
        );
        assertEquals("Hello, World - from file!", getStdOutErr().getLocalStdOut().toString().trim());
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
    }

}
