import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Arrays.stream;

class UbiquitousLanguageJavaParser {
    private final File directory;

    UbiquitousLanguageJavaParser(File directory) {
        this.directory = directory;
    }

    String produceReport() {
        StringBuilder out = new StringBuilder();
        File[] allFiles = directory.listFiles();
        if (allFiles == null) {
            throw new RuntimeException();
        }
        Map<String, Integer> words = new HashMap<>();
        stream(allFiles).forEach(file -> {
            try {
                CompilationUnit compilationUnit = JavaParser.parse(file);
                new VoidVisitorAdapter<Void>() {
                    void visitComment(final Comment n, final Void arg) {
                        if (n != null) {
                            n.accept(this, arg);
                        }
                    }

                    @Override
                    public void visit(ImportDeclaration n, Void arg) {
                        visitComment(n.getComment(), arg);
                        new NameExpr(n.getName().getBeginLine(), n.getName().getBeginColumn(), n.getName().getEndLine(), n.getName().getEndColumn(), n.getName().getName().replace("_", " ")).accept(this, arg);
                    }

                    @Override
                    public void visit(NameExpr n, Void arg) {
                        super.visit(n, arg);
                        if (words.containsKey(n.getName())) {
                            words.put(n.getName(), words.get(n.getName()) + 1);
                        } else {
                            words.put(n.getName(), 1);
                        }
                    }
                }.visit(compilationUnit, null);
            } catch (FileNotFoundException | ParseException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
        words.forEach((word, count) -> out.append(format("%d %s%n", count, word)));
        return out.toString();
    }
}
