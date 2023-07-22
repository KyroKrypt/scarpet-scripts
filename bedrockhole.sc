__config() -> {
    'stay_loaded' -> true,
    'commands' -> {
        '' -> '_search'
    }
};

_search() -> (
    dimension = query(player(), 'dimension');
    if(dimension != 'the_nether',
    	print('You can only search for holes in the nether roof!');
        exit();
    );
    
    player_x = ceil(query(player(), 'x'));
    player_y = ceil(query(player(), 'y'));
    player_z = ceil(query(player(), 'z'));
    
    distance_from_128 = abs(128 - player_y);
    if(distance_from_128 > 10, 
    	print('You are too far away from the nether roof!');
        exit();
    );
    
    shapes = [];
    c_for(x = player_x - 10, x <= player_x + 10, x += 1,
    	c_for(z = player_z - 10, z <= player_z + 10, z += 1,
        	found_bedrock = false;
    		c_for(y = 123, y < 127, y += 1,
            	found_block = block(x, y, z);
                if(found_block == 'bedrock',
                	found_bedrock = true;
                );
            );
            if(!found_bedrock,
				shapes += [
					'box', 200,
					'from', [x, 123, z],
					'to', [x + 1, 130, z + 1],
					'color', 0x734f96ff,
					'fill', 0x734f9622,
				];
            );
        );
    );
    
    print(player(), format('b Found ' + length(shapes) + ' Hole(s)!'));
	draw_shape(shapes);
);