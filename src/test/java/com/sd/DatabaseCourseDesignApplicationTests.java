package com.sd;

import com.sd.common.CodeImage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@SpringBootTest
class DatabaseCourseDesignApplicationTests {

    @Test
    public void getCodeImage() throws IOException {
        BufferedImage codeImage = CodeImage.getVerifiCodeImage();
        String code = new String(CodeImage.getVerifiCode());
        System.out.println(code);
    }

}
