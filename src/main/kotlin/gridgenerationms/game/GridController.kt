package gridgenerationms.game

import gridgenerationms.game.grid.Grid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/grid")
class GridController {

    @GetMapping("")
    fun grid( @RequestParam( value = "height", defaultValue = "50" ) height: Int,
              @RequestParam( value = "width", defaultValue = "50" ) width: Int,
              @RequestParam( value = "seed", defaultValue = "0" ) seed: Long )
    = Grid(height,width , seed )

}