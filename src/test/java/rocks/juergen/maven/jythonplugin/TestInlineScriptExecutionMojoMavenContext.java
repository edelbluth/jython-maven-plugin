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

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * ATTENTION
 * <p>
 * This test is still sub optimal, as the maven plugin test harness does provide the full environment
 */
public class TestInlineScriptExecutionMojoMavenContext extends AbstractMojoTest {

    @Test
    public void test() throws Exception {
        final File pom = new File(TestInlineScriptExecutionMojoMavenContext.class.getResource("poms/context.inline.pom.xml").getFile());
        final InlineScriptExecutionMojo mojo = (InlineScriptExecutionMojo) rule.lookupMojo("inline-script", pom);
        mojo.execute();
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
        final String output = getStdOutErr().getLocalStdOut().toString();
        assertNotNull(output);
        // During invocation via pom, we should see something
        assertEquals("None", output.trim());
    }

}
