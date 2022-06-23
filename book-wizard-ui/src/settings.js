import { createClient, createMicrophoneAndCameraTracks } from "agora-rtc-react";

const appId = "2cc76934147f41c384abbbb64f7fd8a4";
const token = "0062cc76934147f41c384abbbb64f7fd8a4IACFAhoYyTuBrH7ZXbGeCQNT5Qlwf61lWB2JvmRczmi51B9vja0AAAAAEABGROOe1w+2YgEAAQDXD7Zi";

export const config = { mode: "rtc", codec: "vp8", appId: appId, token: token };
export const useClient = createClient(config);
export const useMicrophoneAndCameraTracks = createMicrophoneAndCameraTracks();
export const channelName = "BookWizards";
