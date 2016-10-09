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

import java.io.IOException;
import java.util.Map;

/**
 * This interface describes a Jython Executor and needs to be implemented by all concrete implementation classes.
 */
interface IJythonExecutor
{

    /**
     * Set the context data for the script execution
     *
     * @param context all context data, basically key-value-pairs
     */
    void setContext(Map<String, String> context);

    /**
     * The execution itself
     */
    @SuppressWarnings("RedundantThrows")
    void executeJython() throws IOException;

}
