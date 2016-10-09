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

import org.apache.maven.plugin.AbstractMojo;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class AbstractMojoPomTest extends AbstractMojoTest  {

    private final File pom;
    private final String expectation;
    private final String goal;

    AbstractMojoPomTest(final String pom, final String expectation, final String goal) {
        this.pom = new File(TestInlineScriptExecutionMojo.class.getResource(String.format("poms/%s", pom)).getFile());
        this.expectation = expectation;
        this.goal = goal;
    }

    @Test
    public void test() throws Exception {
        final AbstractMojo mojo = (AbstractMojo) rule.lookupMojo(goal, pom);
        assertNotNull(mojo);
        mojo.execute();
        assertEquals(expectation, getStdOutErr().getLocalStdOut().toString().trim());
        assertEquals("", getStdOutErr().getLocalStdErr().toString());
    }

}
