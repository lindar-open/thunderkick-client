package com.lindar.thunderkick.api;

import com.lindar.thunderkick.vo.api.ErrorResponse;
import com.lindar.thunderkick.vo.api.Game;
import com.lindar.thunderkick.vo.api.GameListWrapper;
import com.lindar.wellrested.WellRestedRequest;
import com.lindar.wellrested.vo.Result;
import com.lindar.wellrested.vo.ResultBuilder;
import com.lindar.wellrested.vo.WellRestedResponse;
import lindar.acolyte.util.UrlAcolyte;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GameResource {
    private final String apiUrl;
    private final String operator;

    public Result<List<Game>> listGames() {
        WellRestedResponse response = WellRestedRequest.builder().url(UrlAcolyte.safeConcat(apiUrl, Endpoints.GAME.GAME_LIST, operator)).build().get().submit();
        if (!response.isValid()) {
            ErrorResponse errorResponse = response.fromJson().castTo(ErrorResponse.class);
            return ResultBuilder.failed().msg(errorResponse.getErrorMessage()).code(errorResponse.getErrorCode()).buildAndIgnoreData();

        }
        GameListWrapper wrapperResult = response.fromJson().castTo(GameListWrapper.class);
        if (wrapperResult == null) {
            return ResultBuilder.failed("Failed to cast to GameListWrapper");
        }
        return ResultBuilder.successful(wrapperResult.getGames().keySet().stream().map(gameId -> new Game(gameId, wrapperResult.getGames().get(gameId))).collect(Collectors.toList()));
    }
}
