#version 460 core

in vec3 pasColor;
in vec2 passTextureCoord;

out vec4 outColor;

uniform sampler2D tex;

void main(){
    outColor = texture(tex, passTextureCoord);
}