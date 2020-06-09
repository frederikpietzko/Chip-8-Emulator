

commands = """00E0 00EE 1NNN 2NNN 3XNN 4XNN 5XY0 6XNN 7XNN 8XY0 8XY1 8XY2 8XY3 8XY4 8XY5 8XY6 8XY7 8XYE 9XY0 ANNN BNNN CXNN DXYN EX9E EXA1 FX07 FX0A FX15 FX18 FX1E FX29 FX33 FX55"""

commands = commands.split(' ')

for command in commands:
    with open(f'x{command}.java', 'w+') as file:
        file.write("""package commands;
        import chip.Chip2;
        import chip.OpcodeCommand;

        public class %s extends OpcodeCommand {

            public %s(Chip2 chip) {
                super(chip);
            }

            @Override
            public void command(Character arg0) {
                // TODO Auto-generated method stub
            }

        }

        """ % (f'x{command}', f'x{command}'))
    
    

