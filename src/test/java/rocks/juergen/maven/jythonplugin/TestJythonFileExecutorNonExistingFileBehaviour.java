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
import org.python.core.PyException;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestJythonFileExecutorNonExistingFileBehaviour extends AbstractStdOutErrTest {

    @Test(expected = PyException.class)
    public void testNonExistingFileException() {
        final HashMap<String, String> context = new HashMap<>();
        context.put(JythonFileExcecutor.SCRIPT_FILE_INDEX, "/oh/no/this/does/not/exist.py");
        final JythonFileExcecutor executor = new JythonFileExcecutor();
        executor.setContext(context);
        executor.executeJython();
    }

    @Test
    public void testNonExistingFileStdOutErr() {
        final HashMap<String, String> context = new HashMap<>();
        context.put(JythonFileExcecutor.SCRIPT_FILE_INDEX, "/oh/no/this/does/not/exist.py");
        final JythonFileExcecutor executor = new JythonFileExcecutor();
        executor.setContext(context);
        try {
            executor.executeJython();
        }
        catch (PyException e) {
            assertNotNull(e);
        }
        assertEquals("", getStdOutErr().getLocalStdOut().toString());
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
    }

}
