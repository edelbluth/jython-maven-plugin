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

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class TestExecuteMojo extends AbstractMojoTest {

    private final String expected;
    private final String currentSetting;

    public TestExecuteMojo(final String scriptFile, final String expected) {
        final String resolvedScriptFile = TestExecuteMojo.class.getResource(scriptFile).getFile();
        this.expected = expected;
        currentSetting = System.getProperty("file", null);
        System.setProperty("file", resolvedScriptFile);
    }

    @Parameterized.Parameters(name = "{index}: {0} / {1}")
    public static List<String[]> getParameters() {
        final ArrayList<String[]> parameters = new ArrayList<>();
        parameters.add(new String[]{"helloworld.py", "Hello, World - from file!"});
        parameters.add(new String[]{"helloworld_name_main.py", "Hello, World - from file!"});
        parameters.add(new String[]{"package-test/main.py", "Hello World from Sub Package"});
        return parameters;
    }

    @After
    public void tearDownConfig() {
        if (currentSetting == null) {
            System.clearProperty("file");
        } else {
            System.setProperty("file", currentSetting);
        }
    }

    @Test
    public void test() throws Exception {
        final File minimumPom = new File(TestExecuteMojo.class.getResource("poms/minimum.pom.xml").getFile());
        final ExecuteMojo mojo = (ExecuteMojo) rule.lookupMojo("exec", minimumPom);
        assertNotNull(mojo);
        mojo.execute();
        assertEquals(expected, getStdOutErr().getLocalStdOut().toString().trim());
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
    }

}
