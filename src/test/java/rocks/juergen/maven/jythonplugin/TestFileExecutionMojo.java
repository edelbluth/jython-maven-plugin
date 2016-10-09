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

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class TestFileExecutionMojo extends AbstractMojoPomTest {

    @Parameterized.Parameters(name = "{index}: {0} / {1}")
    public static List<String[]> getParameters() {
        final ArrayList<String[]> parameters = new ArrayList<>();
        parameters.add(new String[]{"simple-script.script.pom.xml", "Hello, World - from file!"});
        parameters.add(new String[]{"usingstdlib.script.pom.xml", "ll-l"});
        parameters.add(new String[]{"package-test.script.pom.xml", "Hello World from Sub Package"});
        return parameters;
    }

    public TestFileExecutionMojo(final String pom, final String expectation) {
        super(pom, expectation, "file");
    }

}
