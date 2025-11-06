import {IUser} from './user';
import {ITag} from '$core/types/tag';

export type ICodeExplanation = {
  id: string;
  title: string;
  description: string;
  lineNumber?: number;
  startLine?: number;
  endLine?: number;
};

export type ISnippet = {
  id: number;
  title: string,
  description: string,
  author: IUser;
  tags: ITag[];
  content: string;
  language: string;
  explanations?: ICodeExplanation[];
};

