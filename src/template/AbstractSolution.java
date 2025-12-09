package template;

import java.nio.file.Paths;

public abstract class AbstractSolution {

    protected static String getFilePath(String fileName, Class<? extends AbstractSolution> clazz) {
        return Paths.get("src", clazz.getPackageName().replace(".", "/"), fileName).toString();
    }
}
