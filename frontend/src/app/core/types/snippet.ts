import {IUser} from './user';
import {ITag} from '$core/types/tag';

export type ISnippet = {
  id: number;
  title: string,
  description: string,
  author: IUser;
  tags: ITag[];
  content: string;
};

