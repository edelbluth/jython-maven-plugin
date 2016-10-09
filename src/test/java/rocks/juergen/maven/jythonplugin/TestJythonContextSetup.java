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

import static org.junit.Assert.assertEquals;

public class TestJythonContextSetup extends AbstractStdOutErrTest {

    @Test
    public void testInlineScript() throws IllegalAccessException, InstantiationException, IOException {
        final String pythonScript = "print FROM_CONTEXT_COMES_THIS";
        JythonExecutor.execute(
                JythonScriptExecutor.class,
                new AbstractMap.SimpleEntry<>(JythonScriptExecutor.INLINE_SCRIPT_INDEX, pythonScript),
                new AbstractMap.SimpleEntry<>("FROM_CONTEXT_COMES_THIS", "Hello, Context World!")
        );
        assertEquals("Hello, Context World!", getStdOutErr().getLocalStdOut().toString().trim());
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
    }

    @Test
    public void testFile() throws IllegalAccessException, InstantiationException, IOException {
        JythonExecutor.execute(
                JythonFileExcecutor.class,
                new AbstractMap.SimpleEntry<>(JythonFileExcecutor.SCRIPT_FILE_INDEX, TestJythonContextSetup.class.getResource("context.py").getFile()),
                new AbstractMap.SimpleEntry<>("set_by_context", "Hello, File Context World!")
        );
        assertEquals("Hello, File Context World!", getStdOutErr().getLocalStdOut().toString().trim());
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
    }

}
