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

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestJythonExecutionFeatures extends AbstractStdOutErrTest {

    private final String configKey;
    private final String configValue;
    private final Class<? extends IJythonExecutor> executor;
    private final String expectation;

    public TestJythonExecutionFeatures(final Class<? extends IJythonExecutor> executor, final String configKey, final String configValue, final String expectation) {
        this.executor = executor;
        this.configKey = configKey;
        this.configValue = configValue;
        this.expectation = expectation;
    }

    private static String getFile(final String file) {
        return TestJythonExecutionFeatures.class.getResource(file).getFile();
    }

    @Parameterized.Parameters(name = "{index}: {0} / {1} / {2} / {3}")
    public static List<Object[]> getParameters() {
        final ArrayList<Object[]> parameters = new ArrayList<>();
        parameters.add(new Object[]{JythonFileExcecutor.class, JythonFileExcecutor.SCRIPT_FILE_INDEX, getFile("package-test/main.py"), "Hello World from Sub Package"});
        parameters.add(new Object[]{JythonFileExcecutor.class, JythonFileExcecutor.SCRIPT_FILE_INDEX, getFile("stdlib/usingstdlib.py"), "ll-l"});
        return parameters;
    }

    @Test
    public void test() throws IllegalAccessException, InstantiationException, IOException {
        JythonExecutor.execute(executor, new AbstractMap.SimpleEntry<>(configKey, configValue));
        assertEquals(expectation, getStdOutErr().getLocalStdOut().toString().trim());
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
    }

}
