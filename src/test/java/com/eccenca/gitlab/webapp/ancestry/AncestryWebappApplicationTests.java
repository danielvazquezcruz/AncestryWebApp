package com.eccenca.gitlab.webapp.ancestry;

import com.eccenca.gitlab.webapp.ancestry.controller.AncestryController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@WebMvcTest(AncestryController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class AncestryWebappApplicationTests {

    @Test
    public void contextLoads() {
    }



}
