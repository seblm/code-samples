package box2dconcat;

import org.junit.jupiter.api.Test;

import java.io.*;

import static java.io.File.separatorChar;
import static java.lang.System.out;
import static java.lang.System.setOut;
import static org.assertj.core.api.Assertions.assertThat;

public class Box2dConcatTest {

    public static final String COPYRIGHT = "/*\n" +
            "* Copyright (c) 2006-2007 Erin Catto http:\n" +
            "*\n" +
            "* This software is provided 'as-is', without any express or implied\n" +
            "* warranty.  In no event will the authors be held liable for any damages\n" +
            "* arising from the use of this software.\n" +
            "* Permission is granted to anyone to use this software for any purpose,\n" +
            "* including commercial applications, and to alter it and redistribute it\n" +
            "* freely, subject to the following restrictions:\n" +
            "* 1. The origin of this software must not be misrepresented; you must not\n" +
            "* claim that you wrote the original software. If you use this software\n" +
            "* in a product, an acknowledgment in the product documentation would be\n" +
            "* appreciated but is not required.\n" +
            "* 2. Altered source versions must be plainly marked, and must not be\n" +
            "* misrepresented the original software.\n" +
            "* 3. This notice may not be removed or altered from any source distribution.\n" +
            "*/\n";
    public static final String FILE_CONTENT = "\n" +
            "\n" +
            "\n" +
            "// A manifold for two touching convex shapes.\n" +
            "var b2AABB = function() {\n" +
            "\t\t// initialize instance variables for references\n" +
            "\t\tthis.minVertex = new b2Vec2();\n" +
            "\t\tthis.maxVertex = new b2Vec2();\n" +
            "};\n" +
            "\n" +
            "b2AABB.prototype = \n" +
            "{\n" +
            "\tIsValid: function(){\n" +
            "\t\t//var d = b2Math.SubtractVV(this.maxVertex, this.minVertex);\n" +
            "\t\tvar dX = this.maxVertex.x;\n" +
            "\t\tvar dY = this.maxVertex.y;\n" +
            "\t\tdX = this.maxVertex.x;\n" +
            "\t\tdY = this.maxVertex.y;\n" +
            "\t\tdX -= this.minVertex.x;\n" +
            "\t\tdY -= this.minVertex.y;\n" +
            "\t\tvar valid = dX >= 0.0 && dY >= 0.0;\n" +
            "\t\tvalid = valid && this.minVertex.IsValid() && this.maxVertex.IsValid();\n" +
            "\t\treturn valid;\n" +
            "\t},\n" +
            "\n" +
            "\tminVertex: new b2Vec2(),\n" +
            "\tmaxVertex: new b2Vec2()\n" +
            "};\n";

    @Test
    public void should_read_a_file_and_remove_its_copyright_information() {
        assertThat(Box2dConcat.removeCopyright(COPYRIGHT + FILE_CONTENT)).isEqualTo(FILE_CONTENT);
    }

    @Test
    public void should_add_file_name_at_the_begining_of_file() {
        assertThat(Box2dConcat.concat("collision/b2AABB.js", FILE_CONTENT))
                .isEqualTo("// collision/b2AABB.js\n" + FILE_CONTENT);
    }

    @Test
    public void should_concat_all_files_from_a_directory_recursive() throws IOException {
        File f = new File("." + separatorChar + "directory" + separatorChar + "sub-directory");
        f.mkdirs();
        File f2 = new File("." + separatorChar + "directory" + separatorChar + "b2init.js");
        FileWriter in = new FileWriter(f2);
        try {
            in.write(COPYRIGHT);
            in.write(FILE_CONTENT);
        } finally {
            in.close();
        }
        File f3 = new File("." + separatorChar + "directory" + separatorChar + "sub-directory" + separatorChar + "b2class.js");
        in = new FileWriter(f3);
        try {
            in.write(COPYRIGHT);
            in.write("var b2class = '';\n");
        } finally {
            in.close();
        }

        File f4 = new File("." + separatorChar + "directory");

        ByteArrayOutputStream concatOut = new ByteArrayOutputStream();
        PrintStream originalOut = out;
        setOut(new PrintStream(concatOut));

        Box2dConcat.main(new String[]{f4.toString()});

        assertThat(f3.delete()).isTrue();
        assertThat(f.delete()).isTrue();
        assertThat(f2.delete()).isTrue();
        assertThat(f4.delete()).isTrue();
        setOut(originalOut);

        assertThat(f4).doesNotExist();

        assertThat(concatOut.toString()).isEqualTo("// b2init.js\n" + FILE_CONTENT + "\n// sub-directory/b2class.js\nvar b2class = '';\n\n");
    }
}
