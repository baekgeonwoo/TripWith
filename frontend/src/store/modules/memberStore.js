import { login, logout, getMemberInfo } from "@/api/member.js"

const memberStore = {
    namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isValidToken: false,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_USER_INFO_NULL: (state) => {
      state.isLogin = false;
      state.userInfo = null;
    }
  },
  actions: {
    async userConfirm({ commit }, loginRequestDto) {
        await login(
            loginRequestDto,
            (accessToken, refreshToken) => {
                sessionStorage.setItem("access-token", accessToken);
                sessionStorage.setItem("refresh-token", refreshToken);
                console.log(`access/refresh tokens are stored in session storage: ${accessToken}, ${refreshToken}`);
                commit("SET_IS_LOGIN", true);
                commit("SET_IS_LOGIN_ERROR", false);
                commit("SET_IS_VALID_TOKEN", true);
            //   if (data.message === "success") {
            //     let accessToken = data["access-token"];
            //     let refreshToken = data["refresh-token"];
            //     // console.log("login success token created!!!! >> ", accessToken, refreshToken);
            //     commit("SET_IS_LOGIN", true);
            //     commit("SET_IS_LOGIN_ERROR", false);
            //     commit("SET_IS_VALID_TOKEN", true);
            //     sessionStorage.setItem("access-token", accessToken);
            //     sessionStorage.setItem("refresh-token", refreshToken);
            //   } else {
            //     commit("SET_IS_LOGIN", false);
            //     commit("SET_IS_LOGIN_ERROR", true);
            //     commit("SET_IS_VALID_TOKEN", false);
            //   }
            },
            () => {
                commit("SET_IS_LOGIN", false);
                commit("SET_IS_LOGIN_ERROR", true);
                commit("SET_IS_VALID_TOKEN", false);
            }
        );
    },
    async getUserInfo({ commit }, token) {
      console.log("getUserInfo");
      getMemberInfo(token, (memberInfo) => {
        console.log("getMemberInfo");
        commit("SET_USER_INFO", memberInfo);
      },
        (error) => {
          console.log(error);
      })
    },
    // async tokenRegeneration({ commit, state }) {
    //   console.log("토큰 재발급 >> 기존 토큰 정보 : {}", sessionStorage.getItem("access-token"));
    //   await tokenRegeneration(
    //     JSON.stringify(state.userInfo),
    //     ({ data }) => {
    //       if (data.message === "success") {
    //         let accessToken = data["access-token"];
    //         console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
    //         sessionStorage.setItem("access-token", accessToken);
    //         commit("SET_IS_VALID_TOKEN", true);
    //       }
    //     },
    //     async (error) => {
    //       // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
    //       if (error.response.status === 401) {
    //         console.log("갱신 실패");
    //         // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
    //         await logout(
    //           state.userInfo.userid,
    //           ({ data }) => {
    //             if (data.message === "success") {
    //               console.log("리프레시 토큰 제거 성공");
    //             } else {
    //               console.log("리프레시 토큰 제거 실패");
    //             }
    //             alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
    //             commit("SET_IS_LOGIN", false);
    //             commit("SET_USER_INFO", null);
    //             commit("SET_IS_VALID_TOKEN", false);
    //             router.push({ name: "login" });
    //           },
    //           (error) => {
    //             console.log(error);
    //             commit("SET_IS_LOGIN", false);
    //             commit("SET_USER_INFO", null);
    //           }
    //         );
    //       }
    //     }
    //   );
    // },
    async userLogout({ commit }) {
      await logout(
        () => {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_VALID_TOKEN", false);
            commit("SET_USER_INFO_NULL");
        },
        (error) => {
          console.log(error);
          commit("SET_IS_LOGIN", false);
            commit("SET_IS_VALID_TOKEN", false);
            commit("SET_USER_INFO_NULL");
        }
      );
    },
  },
}

export { memberStore }