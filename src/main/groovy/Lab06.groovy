/**
 * @author yaroslav.yermilov
 */

def text = '''
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tristique libero ac finibus eleifend. Sed vulputate ultrices turpis, eu semper est elementum ut. Proin vitae lorem ligula. Integer a quam cursus, fringilla dolor non, tempus erat. Suspendisse ultrices turpis libero, ut euismod eros ultrices in. Pellentesque ut consequat augue, id aliquam nisi. Mauris lacinia in nibh quis convallis. In tincidunt ut felis eget maximus. Curabitur sodales orci massa, nec porta mi cursus lacinia. Pellentesque nec lectus sit amet erat consequat tincidunt et rutrum neque. Aliquam a pharetra nisl. Morbi eu nunc in elit rhoncus consequat. Curabitur sed quam commodo, luctus ante eget, iaculis libero. Proin ultricies hendrerit eros, vitae ultrices magna.

Suspendisse dictum auctor metus ac tincidunt. Donec id arcu suscipit, tincidunt odio non, vulputate odio. Vivamus tempus, odio ac ullamcorper hendrerit, lacus odio pretium sapien, a eleifend lorem elit eu magna. Praesent cursus lacinia nulla venenatis placerat. Fusce sit amet urna eleifend, placerat enim eu, gravida lectus. In eu nibh tempor, molestie sapien eget, sollicitudin metus. Aliquam malesuada lorem ac molestie porta. Integer rutrum sem arcu, id dictum lorem pellentesque eu. Maecenas vel nibh ex. Duis id varius quam.

Aliquam tristique lectus eu aliquam auctor. Aliquam scelerisque odio ut arcu consequat convallis. Maecenas eu porttitor erat. Sed sit amet maximus elit. Curabitur vestibulum non urna eu volutpat. Suspendisse potenti. Nullam at magna scelerisque, imperdiet justo ut, pulvinar velit. Mauris consequat vel est sit amet suscipit. Nulla vestibulum vitae ex vitae molestie. Fusce eleifend lacinia tristique. Sed rutrum, lectus vitae aliquet sagittis, ex lacus bibendum sem, sit amet efficitur elit dui nec sapien. Duis ut sagittis enim. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec sem ex, aliquet nec ligula placerat, efficitur congue lorem. Suspendisse hendrerit justo vitae auctor pulvinar.

Sed commodo cursus mauris, eu euismod lorem blandit nec. Sed ac rhoncus leo, nec sollicitudin tortor. Etiam rutrum, dolor in tempor maximus, sem leo aliquam ligula, ac rhoncus eros arcu id erat. Vivamus id neque mi. Pellentesque tincidunt at magna sit amet faucibus. Ut auctor, nisi non vestibulum fermentum, nisl dolor tempor ex, eu semper sapien quam laoreet ligula. Ut imperdiet ultricies massa sed ullamcorper. Phasellus faucibus lobortis urna et hendrerit. In lacinia mattis dui luctus pulvinar. Cras placerat nec lorem a congue. Pellentesque a suscipit sapien, ac tincidunt diam.

Maecenas ullamcorper felis eu sem ultrices, vel vulputate ligula volutpat. Donec molestie finibus justo, ac mollis eros venenatis vel. In id neque nibh. Sed porttitor sem et rhoncus dapibus. Quisque tempor malesuada dolor sit amet fringilla. In sed varius ex. Nam ornare est a pellentesque interdum. Praesent varius ex sit amet erat porttitor ullamcorper. Phasellus ultricies libero non egestas commodo. Duis mollis fringilla orci at cursus. Quisque tincidunt nisi quis nunc consequat egestas.
'''

text
    .tokenize()

    .collect{ String token -> token.toLowerCase() }

    .groupBy { token -> token }

    .collect{ token, tokens -> [token: token, count: tokens.size()] }

    .sort { o1, o2 -> o2.count <=> o1.count }

    .toList()[0..<10]

    .each { println "$it.token".padLeft(15) + "${it.count}".padLeft(5) }