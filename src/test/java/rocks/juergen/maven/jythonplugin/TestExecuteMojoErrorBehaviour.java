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

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.python.core.PyException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestExecuteMojoErrorBehaviour extends AbstractMojoTest {

    private final String currentSetting;
    private final Class<? extends Exception> expected;

    public TestExecuteMojoErrorBehaviour(final String file, final Class<? extends Exception> expected) {
        this.expected = expected;
        currentSetting = System.getProperty("file", null);
        if (file == null) {
            System.clearProperty("file");
        } else {
            System.setProperty("file", file);
        }
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static List<Object[]> getParameters() {
        final ArrayList<Object[]> parameters = new ArrayList<>();
        parameters.add(new Object[]{null, MojoExecutionException.class});
        parameters.add(new Object[]{"", PyException.class});
        parameters.add(new Object[]{"/oh/no/this/does/not/exist.py", PyException.class});
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
        try {
            mojo.execute();
            fail("No Exception thrown");
        } catch (Exception e) {
            assertTrue(e.getClass().isAssignableFrom(expected));
        }
    }

}
