package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DetailsCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameEqualKeywordPredicate;

class DetailsCommandParserTest {

    private DetailsCommandParser parser = new DetailsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DetailsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsDetailsCommand() {
        DetailsCommand expectedFindCommand =
                new DetailsCommand(new NameEqualKeywordPredicate(new Name("Alice Bob")));

        // No trailing and leading whitespace
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // Leading whitespace
        assertParseSuccess(parser, "      Alice Bob", expectedFindCommand);

        // Trailing whitespace
        assertParseSuccess(parser, "Alice Bob    ", expectedFindCommand);

        // Leading and trailing whitespace
        assertParseSuccess(parser, "     Alice Bob    ", expectedFindCommand);
    }
}