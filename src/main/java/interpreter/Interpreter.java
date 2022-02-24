/**
 *
 */
package interpreter;

import Exceptions.OperandNotParsableException;
import enviroment.Enviroment;
import enviroment.NumberConversion;
import simulator.*;

/**
 * OpcodeInterpreter
 *
 * @author Matthias Oehme
 */
public class Interpreter {

    /**
     * Liest ein Add Befehl mit 2 Operanden vom Typ byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command add_B_2(int adress) {

        Operand one;
        Operand two;
        try {
            one = getOperand(1);
            two = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Add(0, adress, 1, one, two, 0, 0, false);
    }

    /**
     * Liest ein Add Befehl mit 3 Operanden vom Type byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command add_B_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(1);
            two = getOperand(1);
            three = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Add(0, adress, 1, one, two, three, 0, 0, false);
    }

    /**
     * Liest ein Add Befehl mit 2 Operanden vom Typ double ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command add_D_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(8);
            two = getOperand(8);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Add(0, adress, 8, one, two, 0, 0, true);
    }

    /**
     * Liest ein Add Befehl mit 2 Operanden vom Typ float ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command add_F_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Add(0, adress, 4, one, two, 0, 0, true);
    }

    /**
     * Liest ein Add Befehl mit 2 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command add_H_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(2);
            two = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Add(0, adress, 2, one, two, 0, 0, false);
    }

    /**
     * Liest ein Add Befehl mit 3 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command add_H_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(2);
            two = getOperand(2);
            three = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Add(0, adress, 2, one, two, three, 0, 0, false);
    }

    /**
     * Liest ein Add Befehl mit 2 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command add_W_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Add(0, adress, 4, one, two, 0, 0, false);
    }

    /**
     * Liest ein Add Befehl mit 3 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command add_W_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Add(0, adress, 4, one, two, three, 0, 0, false);
    }

    /**
     * Liest ein Add Befehl mit 3 Operanden vom Typ Double ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command add_D_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(8);
            two = getOperand(8);
            three = getOperand(8);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Add(0, adress, 8, one, two, three, 0, 0, true);
    }

    /**
     * Liest ein Add Befehl mit 3 Operanden vom Typ Float ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command add_F_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Add(0, adress, 4, one, two, three, 0, 0, true);
    }

    /**
     * Liest ein Andnot Befehl mit 2 Operanden vom Typ byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command andnot_B_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(2);
            two = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new AndNot(0, adress, 2, one, two, 0, 0);
    }

    /**
     * Liest ein Andnot Befehl mit 3 Operanden vom Typ byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command andnot_B_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(1);
            two = getOperand(1);
            three = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new AndNot(0, adress, 1, one, two, three, 0, 0);
    }

    /**
     * Liest ein Andnot Befehl mit 2 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command andnot_H_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(2);
            two = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new AndNot(0, adress, 2, one, two, 0, 0);
    }

    /**
     * Liest ein Andnot Befehl mit 2 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command andnot_H_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(2);
            two = getOperand(2);
            three = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new AndNot(0, adress, 2, one, two, three, 0, 0);
    }

    /**
     * Liest ein Andnot Befehl mit 2 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command andnot_W_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new AndNot(0, adress, 4, one, two, 0, 0);
    }

    /**
     * Liest ein Andnot Befehl mit 3 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command andnot_W_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new AndNot(0, adress, 4, one, two, three, 0, 0);

    }

    /**
     * Liest ein Call Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command call(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Call(0, adress, 4, one, 0, 0);
    }

    /**
     * Liest ein Clear vom Typ Byte Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command clear_B(int adress) {
        Operand one;
        try {
            one = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Clear(0, adress, 1, one, 0, 0, false);
    }

    /**
     * Liest ein Clear vom Typ Double ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command clear_D(int adress) {
        Operand one;
        try {
            one = getOperand(8);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Clear(0, adress, 8, one, 0, 0, true);
    }

    /**
     * Liest ein Clear vom Typ float ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command clear_F(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Clear(0, adress, 4, one, 0, 0, true);
    }

    /**
     * Liest ein Clear vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command clear_H(int adress) {
        Operand one;
        try {
            one = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Clear(0, adress, 2, one, 0, 0, false);
    }

    /**
     * Liest ein Clear vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command clear_W(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Clear(0, adress, 4, one, 0, 0, false);
    }

    /**
     * Liest ein CMP vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command cmp_B(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(1);
            two = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Cmp(0, adress, 1, one, two, 0, 0, false);
    }

    /**
     * Liest ein Conv ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command conv(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(1);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Conv(0, adress, one, two, 0, 0);
    }

    /**
     * Liest ein CMP vom Typ Double ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command cmp_D(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(8);
            two = getOperand(8);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Cmp(0, adress, 8, one, two, 0, 0, true);
    }

    /**
     * Liest ein CMP vom Typ Float ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command cmp_F(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Cmp(0, adress, 4, one, two, 0, 0, true);
    }

    /**
     * Liest ein CMP vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command cmp_H(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(2);
            two = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Cmp(0, adress, 2, one, two, 0, 0, false);
    }

    /**
     * Liest ein CMP vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command cmp_W(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Cmp(0, adress, 4, one, two, 0, 0, false);
    }

    /**
     * Liest ein Div mit 2 Operanden vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command div_B_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(1);
            two = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Div(0, adress, 1, one, two, 0, 0, false);
    }

    /**
     * Liest ein Div mit 3 Operanden vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command div_B_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(1);
            two = getOperand(1);
            three = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Div(0, adress, 1, one, two, three, 0, 0, false);
    }

    /**
     * Liest ein Div mit 2 Operanden vom Typ Double ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command div_D_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(8);
            two = getOperand(8);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Div(0, adress, 8, one, two, 0, 0, true);
    }

    /**
     * Liest ein Div mit 3 Operanden vom Typ Double ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command div_D_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(8);
            two = getOperand(8);
            three = getOperand(8);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Div(0, adress, 8, one, two, three, 0, 0, true);
    }

    /**
     * Liest ein Div mit 2 Operanden vom Typ Float ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command div_F_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Div(0, adress, 4, one, two, 0, 0, true);
    }

    /**
     * Liest ein Div mit 3 Operanden vom Typ Float ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command div_F_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Div(0, adress, 4, one, two, three, 0, 0, true);
    }

    /**
     * Liest ein Div mit 2 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command div_H_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(2);
            two = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Div(0, adress, 2, one, two, 0, 0, false);
    }

    /**
     * Liest ein Div mit 3 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command div_H_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(2);
            two = getOperand(2);
            three = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Div(0, adress, 2, one, two, three, 0, 0, false);
    }

    /**
     * Liest ein Div mit 2 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command div_W_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Div(0, adress, 4, one, two, 0, 0, false);
    }

    /**
     * Liest ein Div mit 3 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command div_W_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Div(0, adress, 4, one, two, three, 0, 0, false);
    }

    /**
     * Gibt das nächste Byte aus dem Speicher zurück und erhöht den PC
     *
     * @return the next byte
     */
    public static int getNextByte() {
        int ret = Enviroment.getPCFromMemory();
        Enviroment.addToPC(1);
        return ret;
    }

    /**
     * Gibt den nächste Befehl zurück
     *
     * @return nächster Befehl
     */
    public static Command getNextCommand() {
        int pc = Enviroment.getPC();
        int opcode = getNextByte();
        switch (opcode) {
            case 0:
                return new Halt(0, pc, 0, 0);
            case 146:
                return cmp_B(pc);
            case 147:
                return cmp_H(pc);
            case 148:
                return cmp_W(pc);
            case 149:
                return cmp_F(pc);
            case 150:
                return cmp_D(pc);
            case 151:
                return jump_V(pc);
            case 152:
                return jump_NV(pc);
            case 153:
                return clear_B(pc);
            case 154:
                return clear_H(pc);
            case 155:
                return clear_W(pc);
            case 156:
                return clear_F(pc);
            case 157:
                return clear_D(pc);
            case 158:
                return move_B(pc);
            case 159:
                return move_H(pc);
            case 160:
                return move_W(pc);
            case 161:
                return move_F(pc);
            case 162:
                return move_D(pc);
            case 163:
                return moven_B(pc);
            case 164:
                return moven_H(pc);
            case 165:
                return moven_W(pc);
            case 166:
                return moven_F(pc);
            case 167:
                return moven_D(pc);
            case 168:
                return movec_B(pc);
            case 169:
                return movec_H(pc);
            case 170:
                return movec_W(pc);
            case 171:
                return movea(pc);
            case 172:
                return conv(pc);
            case 173:
                return or_B_2(pc);
            case 174:
                return or_H_2(pc);
            case 175:
                return or_W_2(pc);
            case 176:
                return or_B_3(pc);
            case 177:
                return or_H_3(pc);
            case 178:
                return or_W_3(pc);
            case 179:
                return andnot_B_2(pc);
            case 180:
                return andnot_H_2(pc);
            case 181:
                return andnot_W_2(pc);
            case 182:
                return andnot_B_3(pc);
            case 183:
                return andnot_H_3(pc);
            case 184:
                return andnot_W_3(pc);
            case 185:
                return xor_B_2(pc);
            case 186:
                return xor_H_2(pc);
            case 187:
                return xor_W_2(pc);
            case 188:
                return xor_B_3(pc);
            case 189:
                return xor_H_3(pc);
            case 190:
                return xor_W_3(pc);
            case 191:
                return add_B_2(pc);
            case 192:
                return add_H_2(pc);
            case 193:
                return add_W_2(pc);
            case 194:
                return add_F_2(pc);
            case 195:
                return add_D_2(pc);
            case 196:
                return add_B_3(pc);
            case 197:
                return add_H_3(pc);
            case 198:
                return add_W_3(pc);
            case 199:
                return add_F_3(pc);
            case 200:
                return add_D_3(pc);
            case 201:
                return sub_B_2(pc);
            case 202:
                return sub_H_2(pc);
            case 203:
                return sub_W_2(pc);
            case 204:
                return sub_F_2(pc);
            case 205:
                return sub_D_2(pc);
            case 206:
                return sub_B_3(pc);
            case 207:
                return sub_H_3(pc);
            case 208:
                return sub_W_3(pc);
            case 209:
                return sub_F_3(pc);
            case 210:
                return sub_D_3(pc);
            case 211:
                return mult_B_2(pc);
            case 212:
                return mult_H_2(pc);
            case 213:
                return mult_W_2(pc);
            case 214:
                return mult_F_2(pc);
            case 215:
                return mult_D_2(pc);
            case 216:
                return mult_B_3(pc);
            case 217:
                return mult_H_3(pc);
            case 218:
                return mult_W_3(pc);
            case 219:
                return mult_F_3(pc);
            case 220:
                return mult_D_3(pc);
            case 221:
                return div_B_2(pc);
            case 222:
                return div_H_2(pc);
            case 223:
                return div_W_2(pc);
            case 224:
                return div_F_2(pc);
            case 225:
                return div_D_2(pc);
            case 226:
                return div_B_3(pc);
            case 227:
                return div_H_3(pc);
            case 228:
                return div_W_3(pc);
            case 229:
                return div_F_3(pc);
            case 230:
                return div_D_3(pc);
            case 231:
                return sh(pc);
            case 232:
                return rot(pc);
            case 233:
                return jump_EQ(pc);
            case 234:
                return jump_NE(pc);
            case 235:
                return jump_GT(pc);
            case 236:
                return jump_GE(pc);
            case 237:
                return jump_LT(pc);
            case 238:
                return jump_LE(pc);
            case 239:
                return jump_C(pc);
            case 240:
                return jump_NC(pc);
            case 241:
                return jump(pc);
            case 242:
                return call(pc);
            case 243:
                return ret(pc);
            case 244:
                return pushr(pc);
            case 245:
                return popr(pc);
            case 246:
                return exts(pc);
            case 247:
                return ext(pc);
            case 248:
                return ins(pc);
            case 249:
                return finds(pc);
            case 250:
                return findc(pc);
            case 251:
                return jbssi(pc);
            case 252:
                return jbcci(pc);
            default:
                throw new IllegalStateException("Unknown opcode " + opcode);
        }
    }

    /**
     * Liest einen Operanden ein
     *
     * @param length
     *            Datentyp des Operanden
     * @return Operand
     * @throws OperandNotParsableException
     */
    public static Operand getOperand(int length) throws OperandNotParsableException {
        int opcode = getNextByte();

        // Direkte Adressierung <=63
        if (opcode < 64) {
            return new ImmediateOperand(NumberConversion.intToByte(opcode, length),
                    length);
        }

        // Direkte Adressierung sonst
        if (opcode == 143) {
            Enviroment.addToPC(length);
            return new ImmediateOperand(
                    Enviroment.MEMORY.getContent(Enviroment.getPC() - length, length),
                    length);

        }

        // Absolute Adressierung
        if (opcode == 159) {
            Enviroment.addToPC(4);
            return new AbsAddress(NumberConversion.myBytetoIntWithSign(
                    Enviroment.MEMORY.getContent(Enviroment.getPC() - 4, 4)), length,
                    Enviroment.getPC() - 4);
        }

        // Indizierte Adressierung
        if (opcode >= 64 && opcode <= 79) {
            int index = opcode - 64;
            opcode = getNextByte();
            // Relative Adressierung ohne Offset
            if (opcode >= 96 && opcode <= 111) {
                return new RelAddressing(0, opcode - 96, index, length,
                        Enviroment.getPC());
            }

            // Relative Adressierung mit Offset im Byte darstellbar
            if (opcode >= 160 && opcode <= 175) {
                Enviroment.addToPC(1);
                return new RelAddressing(NumberConversion.myBytetoIntWithSign(
                        Enviroment.MEMORY.getContent(Enviroment.getPC() - 1, 1)),
                        opcode - 160, index, length,
                        Enviroment.getPC() - 1);
            }

            // Relative Adressierung mit Offset im Halbwort darstellbar
            if (opcode >= 192 && opcode <= 207) {
                Enviroment.addToPC(2);
                return new RelAddressing(NumberConversion.myBytetoIntWithSign(
                        Enviroment.MEMORY.getContent(Enviroment.getPC() - 2, 2)),
                        opcode - 192, index, length,
                        Enviroment.getPC() - 2);
            }

            // Relative Adressierung mit Offset im Wort darstellbar
            if (opcode >= 224 && opcode <= 239) {
                Enviroment.addToPC(4);
                return new RelAddressing(NumberConversion.myBytetoIntWithSign(
                        Enviroment.MEMORY.getContent(Enviroment.getPC() - 4, 4)),
                        opcode - 224, index, length,
                        Enviroment.getPC() - 4);
            }

            // Indirekte Adressierung mit Offset im byte darstellbar
            if (opcode >= 176 && opcode <= 191) {
                Enviroment.addToPC(1);
                return new IndAddressing(NumberConversion.myBytetoIntWithSign(
                        Enviroment.MEMORY.getContent(Enviroment.getPC() - 1, 1)),
                        opcode - 176, index, length,
                        Enviroment.getPC() - 1);
            }

            // Indirekte Adressierung mit Offset im Halbwort darstellbar
            if (opcode >= 208 && opcode <= 223) {
                Enviroment.addToPC(2);
                return new IndAddressing(NumberConversion.myBytetoIntWithSign(
                        Enviroment.MEMORY.getContent(Enviroment.getPC() - 2, 2)),
                        opcode - 208, index, length,
                        Enviroment.getPC() - 2);
            }

            // Indirekte Adressierung mit Offset im Wort darstellbar
            if (opcode >= 240 && opcode <= 255) {
                Enviroment.addToPC(4);
                return new IndAddressing(NumberConversion.myBytetoIntWithSign(
                        Enviroment.MEMORY.getContent(Enviroment.getPC() - 4, 4)),
                        opcode - 240, index, length,
                        Enviroment.getPC() - 4);
            }
            return null;
        }

        // Register Adressierung
        if (opcode >= 80 && opcode <= 95) {
            return new RegisterAddressing(opcode - 80, length);
        }

        // Relative Adressierung ohne Offset
        if (opcode >= 96 && opcode <= 111) {
            return new RelAddressing(0, opcode - 96, length, Enviroment.getPC());
        }

        // Relative Adressierung mit Offset im Byte darstellbar
        if (opcode >= 160 && opcode <= 175) {
            Enviroment.addToPC(1);
            return new RelAddressing(NumberConversion.myBytetoIntWithSign(
                    Enviroment.MEMORY.getContent(Enviroment.getPC() - 1, 1)),
                    opcode - 160, length, Enviroment.getPC() - 1);
        }

        // Relative Adressierung mit Offset im Halbwort darstellbar
        if (opcode >= 192 && opcode <= 207) {
            Enviroment.addToPC(2);
            return new RelAddressing(NumberConversion.myBytetoIntWithSign(
                    Enviroment.MEMORY.getContent(Enviroment.getPC() - 2, 2)),
                    opcode - 192, length, Enviroment.getPC() - 2);
        }

        // Relative Adressierung mit Offset im Wort darstellbar
        if (opcode >= 224 && opcode <= 239) {
            Enviroment.addToPC(4);
            return new RelAddressing(NumberConversion.myBytetoIntWithSign(
                    Enviroment.MEMORY.getContent(Enviroment.getPC() - 4, 4)),
                    opcode - 224, length, Enviroment.getPC() - 4);
        }

        // Indirekte Adressierung mit Offset im byte darstellbar
        if (opcode >= 176 && opcode <= 191) {
            Enviroment.addToPC(1);
            return new IndAddressing(NumberConversion.myBytetoIntWithSign(
                    Enviroment.MEMORY.getContent(Enviroment.getPC() - 1, 1)),
                    opcode - 176, length, Enviroment.getPC() - 1);
        }

        // Indirekte Adressierung mit Offset im Halbwort darstellbar
        if (opcode >= 208 && opcode <= 223) {
            Enviroment.addToPC(2);
            return new IndAddressing(NumberConversion.myBytetoIntWithSign(
                    Enviroment.MEMORY.getContent(Enviroment.getPC() - 2, 2)),
                    opcode - 208, length, Enviroment.getPC() - 2);
        }

        // Indirekte Adressierung mit Offset im Wort darstellbar
        if (opcode >= 240 && opcode <= 255) {
            Enviroment.addToPC(4);
            return new IndAddressing(NumberConversion.myBytetoIntWithSign(
                    Enviroment.MEMORY.getContent(Enviroment.getPC() - 4, 4)),
                    opcode - 240, length, Enviroment.getPC() - 4);
        }

        // Kelleradressierung
        if (opcode >= 112 && opcode <= 126) {
            return new CellarAddressing(opcode - 112, length, -length, false);
        }

        // Kelleradressierung
        if (opcode >= 128 && opcode <= 142) {
            return new CellarAddressing(opcode - 128, length, length, true);
        }

        throw new OperandNotParsableException();

    }

    /**
     * Liest einen Jump-Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command jump(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Jump(0, adress, one, 8, 0, 0);
    }

    /**
     * Liest einen JumpC-Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command jump_C(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Jump(0, adress, one, 6, 0, 0);
    }

    /**
     * Liest einen JumpEqu-Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command jump_EQ(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Jump(0, adress, one, 0, 0, 0);
    }

    /**
     * Liest einen JumpGE-Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command jump_GE(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Jump(0, adress, one, 3, 0, 0);
    }

    /**
     * Liest einen JumpGT-Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command jump_GT(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Jump(0, adress, one, 2, 0, 0);
    }

    /**
     * Liest einen JumpLE-Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command jump_LE(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Jump(0, adress, one, 5, 0, 0);
    }

    /**
     * Liest einen JumpLT-Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command jump_LT(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Jump(0, adress, one, 4, 0, 0);
    }

    /**
     * Liest einen JumpNC-Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command jump_NC(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Jump(0, adress, one, 7, 0, 0);
    }

    /**
     * Liest einen JumpNE-Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command jump_NE(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Jump(0, adress, one, 1, 0, 0);
    }

    /**
     * Liest einen JumpNV-Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command jump_NV(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Jump(0, adress, one, 10, 0, 0);
    }

    /**
     * Liest einen JumpV-Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command jump_V(int adress) {
        Operand one;
        try {
            one = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Jump(0, adress, one, 9, 0, 0);
    }

    /**
     * Liest einen MOVE Befehl vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command move_B(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(1);
            two = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Move(0, adress, 1, one, two, 0, 0, false);
    }

    /**
     * Liest einen MOVE Befehl vom Typ Double ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command move_D(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(8);
            two = getOperand(8);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Move(0, adress, 8, one, two, 0, 0, true);
    }

    /**
     * Liest einen MOVE Befehl vom Typ Float ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command move_F(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Move(0, adress, 4, one, two, 0, 0, true);
    }

    /**
     * Liest einen MOVE Befehl vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command move_H(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(2);
            two = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Move(0, adress, 2, one, two, 0, 0, false);
    }

    /**
     * Liest einen MOVE Befehl vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command move_W(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Move(0, adress, 4, one, two, 0, 0, false);
    }

    /**
     * Liest einen MOVEA Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command movea(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new MoveA(0, adress, one, two, 0, 0);
    }

    /**
     * Liest einen MOVEC Befehl vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command movec_B(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(1);
            two = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new MoveC(0, adress, 1, one, two, 0, 0);
    }

    /**
     * Liest einen MOVEC Befehl vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command movec_H(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(2);
            two = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new MoveC(0, adress, 2, one, two, 0, 0);
    }

    /**
     * Liest einen MOVEC Befehl vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command movec_W(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new MoveC(0, adress, 4, one, two, 0, 0);
    }

    /**
     * Liest einen MOVEN Befehl vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command moven_B(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(1);
            two = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new MoveN(0, adress, 1, one, two, 0, 0, false);
    }

    /**
     * Liest einen MOVEN Befehl vom Typ Double ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command moven_D(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(8);
            two = getOperand(8);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new MoveN(0, adress, 8, one, two, 0, 0, true);
    }

    /**
     * Liest einen MOVEN Befehl vom Typ Float ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command moven_F(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new MoveN(0, adress, 4, one, two, 0, 0, true);
    }

    /**
     * Liest einen MOVEN Befehl vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command moven_H(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(2);
            two = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new MoveN(0, adress, 2, one, two, 0, 0, false);
    }

    /**
     * Liest einen MOVEN Befehl vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command moven_W(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new MoveN(0, adress, 4, one, two, 0, 0, false);
    }

    /**
     * Liest einen Mult Befehl mit 2 Operanden vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command mult_B_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(1);
            two = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Mult(0, adress, 1, one, two, 0, 0, false);
    }

    /**
     * Liest einen Mult Befehl mit 3 Operanden vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command mult_B_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(1);
            two = getOperand(1);
            three = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Mult(0, adress, 1, one, two, three, 0, 0, false);
    }

    /**
     * Liest einen Mult Befehl mit 2 Operanden vom Typ Double ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command mult_D_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(8);
            two = getOperand(8);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Mult(0, adress, 8, one, two, 0, 0, true);
    }

    /**
     * Liest einen Mult Befehl mit 2 Operanden vom Typ Float ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command mult_F_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Mult(0, adress, 4, one, two, 0, 0, true);
    }

    /**
     * Liest einen Mult Befehl mit 2 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command mult_H_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(2);
            two = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Mult(0, adress, 2, one, two, 0, 0, false);
    }

    /**
     * Liest einen Mult Befehl mit 3 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command mult_H_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(2);
            two = getOperand(2);
            three = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Mult(0, adress, 2, one, two, three, 0, 0, false);
    }

    /**
     * Liest einen Mult Befehl mit 2 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command mult_W_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Mult(0, adress, 4, one, two, 0, 0, false);
    }

    /**
     * Liest einen Mult Befehl mit 3 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command mult_W_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Mult(0, adress, 4, one, two, three, 0, 0, false);
    }

    /**
     * Liest einen Mult Befehl mit 3 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command mult_F_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Mult(0, adress, 4, one, two, three, 0, 0, true);
    }

    /**
     * Liest einen Mult Befehl mit 3 Operanden vom Typ Double ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command mult_D_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(8);
            two = getOperand(8);
            three = getOperand(8);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Mult(0, adress, 8, one, two, three, 0, 0, true);
    }

    /**
     * Liest einen Or Befehl mit 2 Operanden vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command or_B_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(1);
            two = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Or(0, adress, 4, one, two, 0, 0);
    }

    /**
     * Liest einen Or Befehl mit 3 Operanden vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command or_B_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(1);
            two = getOperand(1);
            three = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new AndNot(0, adress, 1, one, two, three, 0, 0);
    }

    /**
     * Liest einen Or Befehl mit 2 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command or_H_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(2);
            two = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Or(0, adress, 4, one, two, 0, 0);
    }

    /**
     * Liest einen Or Befehl mit 3 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command or_H_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(2);
            two = getOperand(2);
            three = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Or(0, adress, 2, one, two, three, 0, 0);
    }

    /**
     * Liest einen Or Befehl mit 2 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command or_W_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Or(0, adress, 4, one, two, 0, 0);
    }

    /**
     * Liest einen Or Befehl mit 3 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command or_W_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Or(0, adress, 4, one, two, three, 0, 0);
    }

    /**
     * Liest einen Pop Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command popr(int adress) {
        return new Popr(0, adress, 0, 0);
    }

    /**
     * Liest einen Pushr Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command pushr(int adress) {
        return new Pushr(0, adress, 4, 0, 0);
    }

    /**
     * Liest einen Ret Befehl ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command ret(int adress) {

        return new Ret(0, adress, 0, 0);
    }

    /**
     * Liest einen Rot Befehl mit 3 Operanden ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command rot(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Rot(0, adress, one, two, three, 0, 0);
    }

    /**
     * Liest einen Sh Befehl mit 3 Operanden ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command sh(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Sh(0, adress, one, two, three, 0, 0);
    }

    /**
     * Liest einen JBSSI Befehl mit 3 Operanden ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command jbssi(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Jbssi(0, adress, one, two, three, 0, 0);
    }

    /**
     * Liest einen JBCCI Befehl mit 3 Operanden ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command jbcci(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Jbcci(0, adress, one, two, three, 0, 0);
    }

    /**
     * Liest einen EXT Befehl mit 4 Operanden ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command ext(int adress) {
        Operand one;
        Operand two;
        Operand three;
        Operand four;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
            four = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Ext(0, adress, one, two, three, four, 0, 0);
    }

    /**
     * Liest einen EXTS Befehl mit 4 Operanden ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command exts(int adress) {
        Operand one;
        Operand two;
        Operand three;
        Operand four;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
            four = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Exts(0, adress, one, two, three, four, 0, 0);
    }

    /**
     * Liest einen INS Befehl mit 4 Operanden ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command ins(int adress) {
        Operand one;
        Operand two;
        Operand three;
        Operand four;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
            four = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Ins(0, adress, one, two, three, four, 0, 0);
    }

    /**
     * Liest einen FINDC Befehl mit 4 Operanden ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command findc(int adress) {
        Operand one;
        Operand two;
        Operand three;
        Operand four;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
            four = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Findc(0, adress, one, two, three, four, 0, 0);
    }

    /**
     * Liest einen FINDS Befehl mit 4 Operanden ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command finds(int adress) {
        Operand one;
        Operand two;
        Operand three;
        Operand four;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
            four = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Finds(0, adress, one, two, three, four, 0, 0);
    }

    /**
     * Liest einen Sub Befehl mit 2 Operanden vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command sub_B_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(1);
            two = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Sub(0, adress, 1, one, two, 0, 0, false);
    }

    /**
     * Liest einen Sub Befehl mit 3 Operanden vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command sub_B_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(1);
            two = getOperand(1);
            three = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Sub(0, adress, 1, one, two, three, 0, 0, false);
    }

    /**
     * Liest einen Sub Befehl mit 2 Operanden vom Typ Double ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command sub_D_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(8);
            two = getOperand(8);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Sub(0, adress, 8, one, two, 0, 0, true);
    }

    /**
     * Liest einen Sub Befehl mit 2 Operanden vom Typ Float ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command sub_F_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Sub(0, adress, 4, one, two, 0, 0, true);
    }

    /**
     * Liest einen Sub Befehl mit 3 Operanden vom Typ Float ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command sub_F_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Sub(0, adress, 4, one, two, three, 0, 0, true);
    }

    /**
     * Liest einen Sub Befehl mit 3 Operanden vom Typ Double ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command sub_D_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(8);
            two = getOperand(8);
            three = getOperand(8);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Sub(0, adress, 8, one, two, three, 0, 0, true);
    }

    /**
     * Liest einen Sub Befehl mit 2 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command sub_H_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(2);
            two = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }
        return new Sub(0, adress, 2, one, two, 0, 0, false);
    }

    /**
     * Liest einen Sub Befehl mit 3 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command sub_H_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(2);
            two = getOperand(2);
            three = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Sub(0, adress, 2, one, two, three, 0, 0, false);
    }

    /**
     * Liest einen Sub Befehl mit 2 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command sub_W_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Sub(0, adress, 4, one, two, 0, 0, false);
    }

    /**
     * Liest einen Sub Befehl mit 3 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command sub_W_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Sub(0, adress, 4, one, two, three, 0, 0, false);
    }

    /**
     * Liest einen XOR Befehl mit 2 Operanden vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command xor_B_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(1);
            two = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Xor(0, adress, 1, one, two, 0, 0);
    }

    /**
     * Liest einen XOR Befehl mit 3 Operanden vom Typ Byte ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command xor_B_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(1);
            two = getOperand(1);
            three = getOperand(1);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Xor(0, adress, 1, one, two, three, 0, 0);
    }

    /**
     * Liest einen XOR Befehl mit 2 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command xor_H_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(2);
            two = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Xor(0, adress, 2, one, two, 0, 0);
    }

    /**
     * Liest einen XOR Befehl mit 3 Operanden vom Typ Halbwort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command xor_H_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(2);
            two = getOperand(2);
            three = getOperand(2);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Xor(0, adress, 2, one, two, three, 0, 0);
    }

    /**
     * Liest einen XOR Befehl mit 2 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command xor_W_2(int adress) {
        Operand one;
        Operand two;
        try {
            one = getOperand(4);
            two = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Xor(0, adress, 2, one, two, 0, 0);
    }

    /**
     * Liest einen XOR Befehl mit 3 Operanden vom Typ Wort ein
     *
     * @param adress
     *            PC
     * @return eingelesener Befehl
     */
    private static Command xor_W_3(int adress) {
        Operand one;
        Operand two;
        Operand three;
        try {
            one = getOperand(4);
            two = getOperand(4);
            three = getOperand(4);
        } catch (OperandNotParsableException e) {
            return null;
        }

        return new Xor(0, adress, 4, one, two, three, 0, 0);
    }

}
