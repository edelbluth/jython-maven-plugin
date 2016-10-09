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

import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.python.core.PySyntaxError;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestInlineScriptExecutionMojoErrorBehaviour extends AbstractStdOutErrTest {

    @Rule
    public final MojoRule rule = new MojoRule()
    {
        @Override
        protected void before() throws Throwable
        {
        }

        @Override
        protected void after()
        {
        }
    };
    private final File pom;
    private final Class<? extends Exception> expected;
    public TestInlineScriptExecutionMojoErrorBehaviour(final String pom, final Class<? extends Exception> expected) {
        this.pom = new File(TestInlineScriptExecutionMojoErrorBehaviour.class.getResource(String.format("poms/%s", pom)).getFile());
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: {0} / {1}")
    public static List<Object[]> getParameters() {
        final ArrayList<Object[]> parameters = new ArrayList<>();
        parameters.add(new Object[]{"syntax-error.inline.pom.xml", PySyntaxError.class});
        return parameters;
    }

    @Test
    public void testForException() throws Exception {
        InlineScriptExecutionMojo mojo = (InlineScriptExecutionMojo) rule.lookupMojo("inline-script", pom);
        assertNotNull(mojo);
        try {
            mojo.execute();
            fail("No Exception thrown!");
        }
        catch (Exception e) {
            assertTrue(e.getClass().isAssignableFrom(expected));
        }
    }

}
