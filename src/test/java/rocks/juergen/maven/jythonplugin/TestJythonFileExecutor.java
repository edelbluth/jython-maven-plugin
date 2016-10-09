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

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestJythonFileExecutor extends AbstractStdOutErrTest {

    private final String file;

    public TestJythonFileExecutor(final String file) {
        final URL resource = TestJythonFileExecutor.class.getResource(file);
        this.file = resource.getFile();
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static List<String[]> getParameters() {
        final ArrayList<String[]> parameters = new ArrayList<>();
        parameters.add(new String[]{"helloworld.py"});
        parameters.add(new String[]{"helloworld_name_main.py"});
        return parameters;
    }

    @Test
    public void testPythonHelloWorldFromFile() {
        final HashMap<String, String> context = new HashMap<>();
        context.put(JythonFileExcecutor.SCRIPT_FILE_INDEX, file);
        final JythonFileExcecutor executor = new JythonFileExcecutor();
        executor.setContext(context);
        executor.executeJython();
        assertEquals("Hello, World - from file!", getStdOutErr().getLocalStdOut().toString().trim());
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
    }

}
